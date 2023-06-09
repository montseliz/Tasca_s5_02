package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.controller.mysql;

import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql.GameDTO;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.Message;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql.PlayerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

public interface IDiceGameController {

    /**
     * POST -> /players/add: crea un jugador/a.
     */
    ResponseEntity<PlayerDTO> addPlayer(@RequestBody PlayerDTO playerDTO) throws Exception;

    /**
     * PUT -> /players/update/{player_id}: modifica el nom del jugador/a.
     */
    ResponseEntity<PlayerDTO> updatePlayer(@PathVariable long player_id, @RequestBody PlayerDTO playerDTO) throws Exception;

    /**
     * POST -> /players/{player_id}/game: un jugador/a específic realitza una tirada de daus.
     */
    ResponseEntity<GameDTO> newGame(@PathVariable long player_id) throws Exception;

    /**
     * DELETE -> /players/{player_id}/games: elimina les tirades del jugador/a.
     */
    ResponseEntity<Message> deleteGames(@PathVariable long player_id, WebRequest request) throws Exception;

    /**
     * GET -> /players/: retorna el llistat de tots els jugadors/es del sistema amb el seu percentatge mitjà d’èxits.
     */
    ResponseEntity<List<PlayerDTO>> getAllPlayers() throws Exception;

    /**
     * GET -> /players/{player_id}/games: retorna el llistat de jugades per un jugador/a.
     */
    ResponseEntity<List<GameDTO>> getAllGamesByPlayer(@PathVariable long player_id) throws Exception;

    /**
     * GET -> /players/ranking: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el percentatge mitjà d’èxits.
     */
    ResponseEntity<Message> getWinAverage(WebRequest request) throws Exception;

    /**
     * GET -> /players/ranking/loser: retorna el jugador/a amb pitjor percentatge d’èxit.
     */
    ResponseEntity<PlayerDTO> getLoser() throws Exception;

    /**
     * GET -> /players/ranking/winner: retorna el jugador amb pitjor percentatge d’èxit.
     */
    ResponseEntity<PlayerDTO> getWinner() throws Exception;

}
