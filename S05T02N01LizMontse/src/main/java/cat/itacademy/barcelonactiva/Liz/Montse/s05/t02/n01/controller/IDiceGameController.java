package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.controller;

import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql.GameDTO;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.Message;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql.PlayerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.WebRequest;

public interface IDiceGameController {

    /**
     * POST -> /players/add: crea un jugador/a.
     */
    ResponseEntity<PlayerDTO> addPlayer(@RequestBody PlayerDTO playerDTO, WebRequest request) throws Exception;

    /**
     * PUT -> /players/update/{player_id}: modifica el nom del jugador/a.
     */
    ResponseEntity<PlayerDTO> updatePlayer(@PathVariable long player_id, @RequestBody PlayerDTO playerDTO, WebRequest request) throws Exception;

    /**
     * POST -> /players/{player_id}/game: un jugador/a específic realitza una tirada de daus.
     */
    ResponseEntity<GameDTO> newGame(@PathVariable long player_id) throws Exception;

    /**
     * DELETE -> /players/{player_id}/games: elimina les tirades del jugador/a.
     */
    ResponseEntity<Message> deleteGames(@PathVariable long player_id, WebRequest request) throws Exception;

}
