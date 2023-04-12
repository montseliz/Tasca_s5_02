package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service;

import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.domain.mysql.Player;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql.PlayerDTO;

import java.util.List;

public interface IPlayerService {

    Player getPlayerById(long id); //TODO: POSSIBLE ELIMINACIÓ SI NO EL FAIG SERVIR
    PlayerDTO getPlayerDTOById(long id);
    List<PlayerDTO> getListPlayers();
    void createPlayer(PlayerDTO playerDTO);
    void editPlayer(long id, PlayerDTO playerDTO);
    List<PlayerDTO> getWinningAverage();
    PlayerDTO getMostLoser();
    PlayerDTO getMostWinner();

}
