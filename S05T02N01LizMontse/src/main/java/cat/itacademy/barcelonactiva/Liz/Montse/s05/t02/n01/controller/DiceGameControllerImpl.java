package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.controller;

import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql.GameDTO;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.Message;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql.PlayerDTO;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.exception.PlayerDuplicatedException;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.exception.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.IGameService;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.IPlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestController
@RequestMapping("/players")
@Tag(name = "Dice Game API", description = "API operations pertaining to Dice Game database")
public class DiceGameControllerImpl implements IDiceGameController{

    private final IPlayerService playerService;
    private final IGameService gameService;

    @Autowired
    public DiceGameControllerImpl(IPlayerService playerService, IGameService gameService) {
        super();
        this.playerService = playerService;
        this.gameService = gameService;
    }

    @Override
    @PostMapping(value = "/add", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Create a new player", description = "Adds a new player into the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Player created correctly", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PlayerDTO.class))}),
            @ApiResponse(responseCode = "406", description = "Player's name not valid", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while creating the player", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<PlayerDTO> addPlayer(@RequestBody PlayerDTO playerDTO, WebRequest request) throws Exception {

        try {
            return new ResponseEntity<>(playerService.createPlayer(playerDTO), HttpStatus.CREATED);
        } catch (PlayerDuplicatedException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while creating the player", e.getCause());
        }
    }

    @Override
    @PutMapping(value = "/update/{player_id}", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Update player's name", description = "Updates an existing player in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player updated correctly", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PlayerDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Player not found by id", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "406", description = "Player's name not valid", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while updating the player", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<PlayerDTO> updatePlayer(@Parameter(description = "The id of the player to be updated") @PathVariable long player_id, @RequestBody PlayerDTO playerDTO, WebRequest request) throws Exception {

        try {
            return new ResponseEntity<>(playerService.editPlayer(player_id, playerDTO), HttpStatus.OK);
        } catch (PlayerNotFoundException e) {
            throw e;
        } catch (PlayerDuplicatedException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while updating the player", e.getCause());
        }
    }

    @Override
    @PostMapping(value = "/{player_id}/game", produces = "application/json")
    @Operation(summary = "Create a new game", description = "Adds a new game into the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Game created correctly", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = GameDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Player not found by id", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while creating the game", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<GameDTO> newGame(@Parameter(description = "The id of the player playing the game") @PathVariable long player_id) throws Exception {

        try {
            return new ResponseEntity<>(gameService.createGame(player_id), HttpStatus.CREATED);
        } catch (PlayerNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while creating the game", e.getCause());
        }
    }

    @Override
    @DeleteMapping(value = "/{player_id}/games", produces = "application/json")
    @Operation(summary = "Delete player's games history", description = "Deletes the player's entire games history from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Games removed successfully", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "404", description = "Player not found by id", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while deleting the player's games history", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<Message> deleteGames(@Parameter(description = "The id of the player whose games are to be deleted") @PathVariable long player_id, WebRequest request) throws Exception {
        try {
            gameService.removeGamesByPlayer(player_id);
            return new ResponseEntity<>(new Message(HttpStatus.OK.value(), new Date(), "Games history removed successfully", request.getDescription(false)), HttpStatus.OK);
        } catch (PlayerNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while deleting games history", e.getCause());
        }
    }



}
