package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service;

import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql.GameDTO;

import java.util.Set;

public interface IGameService {

    Set<GameDTO> getGamesHistoryByPlayer(long player_id);
    GameDTO createGame(long player_id);
    void removeGamesByPlayer(long player_id);

}
