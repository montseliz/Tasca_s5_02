package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.controller.mongodb;

import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.Message;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mongodb.GameMongoDTO;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mongodb.PlayerMongoDTO;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

public interface IDiceGameMongoController {

    /**
     * POST -> /players/add: crea un jugador/a.
     */
    ResponseEntity<PlayerMongoDTO> addPlayer(@RequestBody PlayerMongoDTO playerDTO) throws Exception;

    /**
     * PUT -> /players/update/{id}: modifica el nom del jugador/a.
     */
    ResponseEntity<PlayerMongoDTO> updatePlayer(@PathVariable ObjectId id, @RequestBody PlayerMongoDTO playerDTO) throws Exception;

    /**
     * POST -> /players/{id}/game: un jugador/a específic realitza una tirada de daus.
     */
    ResponseEntity<GameMongoDTO> newGame(@PathVariable ObjectId id) throws Exception;

    /**
     * DELETE -> /players/{id}/games: elimina les tirades del jugador/a.
     */
    ResponseEntity<Message> deleteGames(@PathVariable ObjectId id, WebRequest request) throws Exception;

    /**
     * GET -> /players/: retorna el llistat de tots els jugadors/es del sistema amb el seu percentatge mitjà d’èxits.
     */
    ResponseEntity<List<PlayerMongoDTO>> getAllPlayers() throws Exception;

    /**
     * GET -> /players/{id}/games: retorna el llistat de jugades per un jugador/a.
     */
    ResponseEntity<List<GameMongoDTO>> getAllGamesByPlayer(@PathVariable ObjectId id) throws Exception;

    /**
     * GET -> /players/ranking: retorna el ranking mig de tots els jugadors/es del sistema. És a dir, el percentatge mitjà d’èxits.
     */
    ResponseEntity<Message> getWinAverage(WebRequest request) throws Exception;

    /**
     * GET -> /players/ranking/loser: retorna el jugador/a amb pitjor percentatge d’èxit.
     */
    ResponseEntity<PlayerMongoDTO> getLoser() throws Exception;

    /**
     * GET -> /players/ranking/winner: retorna el jugador amb pitjor percentatge d’èxit.
     */
    ResponseEntity<PlayerMongoDTO> getWinner() throws Exception;

}
