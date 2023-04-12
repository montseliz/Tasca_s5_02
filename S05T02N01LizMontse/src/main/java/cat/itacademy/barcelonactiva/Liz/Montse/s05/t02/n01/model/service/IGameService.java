package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service;

import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql.GameDTO;

import java.util.Set;

public interface IGameService {

    Set<GameDTO> getGamesHistoryByPlayer(long id);
    GameDTO createGame(long id);
    void removeGamesByPlayer(long id);

}
