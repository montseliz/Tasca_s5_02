package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.mysql;

import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql.GameDTO;

import java.util.List;

public interface IGameService {

    GameDTO createGame(long player_id);
    void removeGamesByPlayer(long player_id);
    List<GameDTO> getGamesHistoryByPlayer(long player_id);

}
