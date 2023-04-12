package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service;

import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.domain.mysql.Game;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.domain.mysql.Player;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql.GameDTO;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql.PlayerDTO;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.exception.PlayerDuplicatedException;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.exception.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.repository.mysql.IGameRepository;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.repository.mysql.IPlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    private Player convertPlayerDTOToEntity (PlayerDTO playerDTO) {
        return modelMapper.map(playerDTO, Player.class);
    }

    private GameDTO convertGameToDTO (Game game) {
        return modelMapper.map(game, GameDTO.class);
    }

    private Game convertGameDTOToEntity (GameDTO gameDTO) {
        return modelMapper.map(gameDTO, Game.class);
    }
    //endregion CONVERTERS

    @Override
    public Player getPlayerById(long id) {
        return playerRepository.findById(id).orElseThrow(() -> new PlayerNotFoundException("Player with ID " + id + " not found"));
    }

    @Override
    public PlayerDTO getPlayerDTOById(long id) {
        return playerRepository.findById(id).map(this::convertPlayerToDTO).orElseThrow(() -> new PlayerNotFoundException("Player with ID " + id + " not found"));
    }

    @Override
    public List<PlayerDTO> getListPlayers() {
        if (playerRepository.findAll().isEmpty()) {
            throw new PlayerNotFoundException("There are no players introduced in the database");
        } else {
            return playerRepository.findAll().stream().map(this::convertPlayerToDTO).collect(Collectors.toList());
        }
    }

    @Override
    public void createPlayer(PlayerDTO playerDTO) {
        PlayerDTO playerUnknown = createUnknownPlayer(playerDTO);
        PlayerDTO playerValidated = validatePlayerName(playerUnknown);
        Player playerConverted = convertPlayerDTOToEntity(playerValidated);
        playerRepository.save(playerConverted);
    }

    @Override
    public void editPlayer(long id, PlayerDTO playerDTO) {
        PlayerDTO playerToUpdate = getPlayerDTOById(id);
        PlayerDTO playerUnknown = createUnknownPlayer(playerToUpdate);
        playerUnknown.setName(playerDTO.getName());
        PlayerDTO playerValidated = validatePlayerName(playerUnknown);
        playerRepository.save(convertPlayerDTOToEntity(playerValidated));
    }

    @Override
    public List<PlayerDTO> getWinningAverage() {
        return null;
    }

    @Override
    public PlayerDTO getMostLoser() {
        return null;
    }

    @Override
    public PlayerDTO getMostWinner() {
        return null;
    }

    @Override
    public Set<GameDTO> getGamesHistoryByPlayer(long id) {
        return null;
    }

    @Override
    public GameDTO createGame(long id) {
        PlayerDTO playerDTOById = getPlayerDTOById(id);
        GameDTO newGameDTO = new GameDTO(playerDTOById);
        Game newGameConverted = gameRepository.save(convertGameDTOToEntity(newGameDTO));

        return convertGameToDTO(newGameConverted);
    }

    @Override
    public void removeGamesByPlayer(long id) {

    }

    /**
     * Mètode per comprovar si el nom del Player és únic perquè no es repeteixi a la base de dades i mentre no sigui "unknown".
     * S'utilitza en els mètodes createPlayer() i editPlayer().
     */
    public PlayerDTO validatePlayerName(PlayerDTO playerDTO) {
        if (playerRepository.existsByName(playerDTO.getName()) && !playerDTO.getName().equalsIgnoreCase("unknown")) {
            throw new PlayerDuplicatedException("Player's name must be unique");
        } else {
            return playerDTO;
        }
    }

    /**
     * Mètode per generar el nom del Player a "unknown" en cas que sigui null, buit o en blanc.
     * S'utilitza en els mètodes createPlayer() i editPlayer().
     */
    public PlayerDTO createUnknownPlayer (PlayerDTO playerDTO) {
        if ((playerDTO.getName() == null) || (playerDTO.getName().isEmpty()) || (playerDTO.getName().isBlank())) {
            playerDTO.setName("unknown");
        }
        return playerDTO;
    }
}