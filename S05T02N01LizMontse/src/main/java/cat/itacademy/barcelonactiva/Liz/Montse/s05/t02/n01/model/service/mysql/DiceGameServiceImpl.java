package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.mysql;

import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.domain.mysql.Game;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.domain.mysql.Player;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql.GameDTO;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql.PlayerDTO;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.exception.GamesNotFoundException;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.exception.PlayerDuplicatedException;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.exception.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.repository.mysql.IGameRepository;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.repository.mysql.IPlayerRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.domain.mysql.Game.ResultGame.WINNER;

@Service
public class DiceGameServiceImpl implements IPlayerService, IGameService {

    @Autowired
    private ModelMapper modelMapper;

    private final IPlayerRepository playerRepository;
    private final IGameRepository gameRepository;

    @Autowired
    public DiceGameServiceImpl(IPlayerRepository playerRepository, IGameRepository gameRepository) {
        super();
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    //region CONVERTERS
    private PlayerDTO convertPlayerToDTO (Player player) {
        return modelMapper.map(player, PlayerDTO.class);
    }

    private Player convertDTOToPlayer(PlayerDTO playerDTO) {
        return modelMapper.map(playerDTO, Player.class);
    }

    private GameDTO convertGameToDTO (Game game) {
        return modelMapper.map(game, GameDTO.class);
    }

    private List<GameDTO> convertGameListToDTO(List<Game> gamesHistory) {
        return gamesHistory.stream().map(this::convertGameToDTO).collect(Collectors.toList());
    }
    //endregion CONVERTERS

    @Override
    public Player getPlayerById(long player_id) {
        Optional<Player> player = playerRepository.findById(player_id);
        if(player.isPresent()) {
            return player.get();
        } else {
            throw new PlayerNotFoundException("Player with ID " + player_id + " not found");
        }
    }

    @Override
    public List<Player> getListPlayers() {
        if (playerRepository.findAll().isEmpty()) {
            throw new PlayerNotFoundException("There are no players introduced in the database");
        } else {
            return playerRepository.findAll();
        }
    }

    @Override
    public PlayerDTO createPlayer(PlayerDTO playerDTO) {
        PlayerDTO playerValidated = validatePlayerName(playerDTO);
        Player playerSaved = playerRepository.save(convertDTOToPlayer(playerValidated));
        PlayerDTO playerDTOToReturn = convertPlayerToDTO(playerSaved);
        playerDTOToReturn.setWinPercentage("No games played yet");

        return playerDTOToReturn;
    }

    @Override
    public PlayerDTO editPlayer(long player_id, PlayerDTO playerDTO) {
        Player playerToUpdate = getPlayerById(player_id);

        PlayerDTO playerValidated = validatePlayerName(playerDTO);
        playerToUpdate.setName(playerValidated.getName());

        Player playerUpdated = playerRepository.save(playerToUpdate);

        return obtainWinPercentage(playerUpdated);
    }

    @Override
    public List<PlayerDTO> getPlayersWithWinPercentage() {
        List<Player> allPlayers = getListPlayers();
        List<PlayerDTO> allPlayersDTO = new ArrayList<>();

        allPlayers.forEach(p -> allPlayersDTO.add(obtainWinPercentage(p)));
        return allPlayersDTO;
    }

    @Override
    public String getWinningAverage() {
        List<PlayerDTO> playersWhoPlayed = playersWhoPlayed();

        double totalWinPercentage = playersWhoPlayed.stream().mapToDouble(p -> Double.parseDouble(p.getWinPercentage().replace(",", "."))).sum();

        double winningAverage = totalWinPercentage / playersWhoPlayed.size();

        return "Players average wins: " + String.format("%.2f", winningAverage);
    }

    @Override
    public PlayerDTO getMostLoser() {
        List<PlayerDTO> playersWhoPlayed = playersWhoPlayed();

        return playersWhoPlayed.stream().min(Comparator.comparingDouble(p -> Double.parseDouble(p.getWinPercentage().replace(",", ".")))).get();
    }

    @Override
    public PlayerDTO getMostWinner() {
        List<PlayerDTO> playersWhoPlayed = playersWhoPlayed();

        return playersWhoPlayed.stream().max(Comparator.comparingDouble(p -> Double.parseDouble(p.getWinPercentage().replace(",", ".")))).get();
    }

    @Override
    public GameDTO createGame(long player_id) {
        Player playerById = getPlayerById(player_id);

        Game newGame = new Game(playerById);
        playerById.addGamesHistory(newGame);
        gameRepository.save(newGame);

        return convertGameToDTO(newGame);
    }

    /**
     * Anotació @Transactional indispensable perquè el mètode elimini tots els games del player especificat de la base de dades.
     * És necessària perquè l'eliminació de tots els jocs de l'historial de jocs d'un jugador és una operació que pot afectar a
     * múltiples registres en la base de dades.
     * Utilitzant l'anotació garantim que l'operació d'eliminar es realitzi de manera atòmica, que significa que
     * es compromet o es desfà en cas que es produeixi una excepció.
     * Així s'evita que es faci una eliminació parcial o incompleta que podria deixar la base de dades en un estat inconsistent.
     * Per tant, és indispensable perquè l'eliminació es dugui a terme de manera segura i consistent en la base de dades.
     */
    @Override
    @Transactional
    public void removeGamesByPlayer(long player_id) {
        List<Game> gamesToRemove = ifExistsGames(player_id);

        gamesToRemove.clear();
    }

    @Override
    public List<GameDTO> getGamesHistoryByPlayer(long player_id) {

        return convertGameListToDTO(ifExistsGames(player_id));
    }

    /**
     * Mètode per comprovar si el nom del Player és únic perquè no es repeteixi a la base de dades i mentre no sigui "unknown".
     * S'utilitza en els mètodes createPlayer() i editPlayer().
     */
    public PlayerDTO validatePlayerName(PlayerDTO playerDTO) {
        PlayerDTO playerUnknown = createUnknownPlayer(playerDTO);
        if ((playerRepository.existsByName(playerUnknown.getName())) && (!playerDTO.getName().equalsIgnoreCase("unknown"))) {
            throw new PlayerDuplicatedException("Player's name must be unique");
        } else {
            return playerDTO;
        }
    }

    /**
     * Mètode per generar el nom del Player a "unknown" en cas que sigui null, buit o en blanc.
     * S'utilitza en el mètode validatePlayerName().
     */
    public PlayerDTO createUnknownPlayer (PlayerDTO playerDTO) {
        if ((playerDTO.getName() == null) || (playerDTO.getName().isEmpty()) || (playerDTO.getName().isBlank())) {
            playerDTO.setName("unknown");
        }
        return playerDTO;
    }

    /**
     * Mètode encarregat de calcular el percentatge de victòries del Player.
     * S'utilitza en el mètode editPlayer() i getPlayersWithWinPercentage().
     */
    public PlayerDTO obtainWinPercentage(Player player) {
        Player playerValidated = ifExistsPlayer(player);
        PlayerDTO playerDTOToReturn = convertPlayerToDTO(playerValidated);

        if (playerValidated.getGamesHistory().isEmpty()) {
            playerDTOToReturn.setWinPercentage("Cannot calculate a player's winning percentage with no games played");
        } else {
            long totalGames = playerValidated.getGamesHistory().size();
            long totalWins = playerValidated.getGamesHistory().stream().filter(g -> g.getResult() == WINNER).count();
            double winPercentage = ((double) totalWins / totalGames) * 100.0d;

            playerDTOToReturn.setWinPercentage(String.format("%.2f", winPercentage));
        }
        return playerDTOToReturn;
    }

    /**
     * Mètode per validar l'existència del Player a la base de dades.
     * S'utilitza en el mètode obtainWinPercentage().
     */
    public Player ifExistsPlayer(Player player) {
        if (playerRepository.existsById(player.getPlayer_id())) {
            return player;
        } else {
            throw new PlayerNotFoundException("Player not found in the database");
        }
    }

    /**
     * Mètode per validar l'existència de games per player a la base de dades.
     * S'utilitza en els mètodes removeGamesByPlayer() i getGamesHistoryByPlayer().
     */
    public List<Game> ifExistsGames(long player_id) {
        Player playerById = getPlayerById(player_id);
        if (playerById.getGamesHistory().isEmpty()) {
            throw new GamesNotFoundException("There are no games played by " + playerById.getName());
        } else {
            return playerById.getGamesHistory();
        }
    }

    /**
     * Mètode per obtenir només els jugadors que han jugat.
     * S'utilitza en els mètodes getWinningAverage(), getMostLoser() i getMostWinner().
     */
    public List<PlayerDTO> playersWhoPlayed() {
        List<PlayerDTO> allPlayersDTO = getPlayersWithWinPercentage();

        if (gameRepository.isEmpty()) {
            throw new GamesNotFoundException("There are no games stored in the database");
        } else {
            return allPlayersDTO.stream().filter(p -> !p.getWinPercentage().equals("No games played yet")
                    && !p.getWinPercentage().equals("Cannot calculate a player's winning percentage with no games played")).collect(Collectors.toList());
        }
    }

}