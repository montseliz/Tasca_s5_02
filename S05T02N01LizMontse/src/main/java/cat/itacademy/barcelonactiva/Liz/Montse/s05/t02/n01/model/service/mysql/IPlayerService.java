package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.mysql;

import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.domain.mysql.Player;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql.PlayerDTO;

import java.util.List;

public interface IPlayerService {

    Player getPlayerById(long player_id);
    List<Player> getListPlayers();
    PlayerDTO createPlayer(PlayerDTO playerDTO);
    PlayerDTO editPlayer(long player_id, PlayerDTO playerDTO);
    List<PlayerDTO> getPlayersWithWinPercentage();
    String getWinningAverage();
    PlayerDTO getMostLoser();
    PlayerDTO getMostWinner();

}
