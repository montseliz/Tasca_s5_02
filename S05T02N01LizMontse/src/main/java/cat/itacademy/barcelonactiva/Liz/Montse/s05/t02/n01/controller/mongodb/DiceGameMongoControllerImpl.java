package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.controller.mongodb;

import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.Message;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mongodb.GameMongoDTO;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mongodb.PlayerMongoDTO;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.exception.GamesNotFoundException;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.exception.PlayerDuplicatedException;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.exception.PlayerNotFoundException;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.mongodb.IGameMongoService;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.mongodb.IPlayerMongoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/playersMongo")
@Tag(name = "Dice Game MongoDB API", description = "API operations pertaining to Dice Game MongoDB database")
public class DiceGameMongoControllerImpl implements IDiceGameMongoController{

    private final IPlayerMongoService playerService;
    private final IGameMongoService gameService;

    @Autowired
    public DiceGameMongoControllerImpl(IPlayerMongoService playerService, IGameMongoService gameService) {
        super();
        this.playerService = playerService;
        this.gameService = gameService;
    }

    @Override
    @PostMapping(value = "/add", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Create a new player", description = "Adds a new player into the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Player created correctly", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PlayerMongoDTO.class))}),
            @ApiResponse(responseCode = "406", description = "Player's name not valid", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while creating the player", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<PlayerMongoDTO> addPlayer(@RequestBody PlayerMongoDTO playerDTO) throws Exception {

        try {
            return new ResponseEntity<>(playerService.createPlayer(playerDTO), HttpStatus.CREATED);
        } catch (PlayerDuplicatedException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while creating the player", e.getCause());
        }
    }

    @Override
    @PutMapping(value = "/update/{id}", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Update player's name", description = "Updates an existing player in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player updated correctly", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = PlayerMongoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Player not found by id", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "406", description = "Player's name not valid", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while updating the player", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<PlayerMongoDTO> updatePlayer(@Parameter(description = "The id of the player to be updated") @PathVariable ObjectId id, @RequestBody PlayerMongoDTO playerDTO) throws Exception {

        try {
            return new ResponseEntity<>(playerService.editPlayer(id, playerDTO), HttpStatus.OK);
        } catch (PlayerNotFoundException | PlayerDuplicatedException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while updating the player", e.getCause());
        }
    }

    @Override
    @PostMapping(value = "/{id}/game", produces = "application/json")
    @Operation(summary = "Create a new game", description = "Adds a new game into the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Game created correctly", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = GameMongoDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Player not found by id", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while creating the game", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<GameMongoDTO> newGame(@Parameter(description = "The id of the player playing the game") @PathVariable ObjectId id) throws Exception {

        try {
            return new ResponseEntity<>(gameService.createGame(id), HttpStatus.CREATED);
        } catch (PlayerNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while creating the game", e.getCause());
        }
    }

    @Override
    @DeleteMapping(value = "/{id}/games", produces = "application/json")
    @Operation(summary = "Delete player's games history", description = "Deletes the player's entire games history from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Games removed successfully", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "404", description = "Player not found by id", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "404", description = "Games not found by player", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while deleting the player's games history", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<Message> deleteGames(@Parameter(description = "The id of the player whose games are to be deleted") @PathVariable ObjectId id, WebRequest request) throws Exception {

        try {
            gameService.removeGamesByPlayer(id);
            return new ResponseEntity<>(new Message(HttpStatus.OK.value(), LocalDateTime.now(), "Games history removed successfully", request.getDescription(false)), HttpStatus.OK);
        } catch (PlayerNotFoundException | GamesNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while deleting games history", e.getCause());
        }
    }

    @Override
    @GetMapping(value = "/", produces = "application/json")
    @Operation(summary = "Get all players", description = "Returns a list with all players stored in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of players retrieved successfully", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = PlayerMongoDTO.class)))}),
            @ApiResponse(responseCode = "404", description = "There are no players introduced in the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while retrieving the players from the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<List<PlayerMongoDTO>> getAllPlayers() throws Exception {

        try {
            return new ResponseEntity<>(playerService.getPlayersWithWinPercentage(), HttpStatus.OK);
        } catch (PlayerNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while retrieving the players from the database", e.getCause());
        }
    }

    @Override
    @GetMapping(value = "/{id}/games", produces = "application/json")
    @Operation(summary = "Get all games by player", description = "Returns a list with all games played by the player stored in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of games by player retrieved successfully", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = GameMongoDTO.class)))}),
            @ApiResponse(responseCode = "404", description = "Player not found by id", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "404", description = "Games not found by player", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while retrieving the games by player from the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<List<GameMongoDTO>> getAllGamesByPlayer(@Parameter(description = "The id of the player whose games are to be retrieved") @PathVariable ObjectId id) throws Exception {

        try {
            return new ResponseEntity<>(gameService.getGamesHistoryByPlayer(id), HttpStatus.OK);
        } catch (PlayerNotFoundException | GamesNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while retrieving the games by player from the database", e.getCause());
        }
    }

    @Override
    @GetMapping(value = "/ranking", produces = "application/json")
    @Operation(summary = "Players average wins", description = "Returns the players average wins from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Players average wins retrieved successfully", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Message.class)))}),
            @ApiResponse(responseCode = "404", description = "There are no players introduced in the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "404", description = "Games not found in the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while retrieving the average wins from the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<Message> getWinAverage(WebRequest request) throws Exception {

        try {
            return new ResponseEntity<>(new Message(HttpStatus.OK.value(), LocalDateTime.now(), playerService.getWinningAverage(), request.getDescription(false)), HttpStatus.OK);
        } catch (PlayerNotFoundException | GamesNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while retrieving the average wins from the database", e.getCause());
        }
    }

    @Override
    @GetMapping(value = "/ranking/loser", produces = "application/json")
    @Operation(summary = "Get the most loser player", description = "Returns the most losing player stored in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Most losing player retrieved successfully", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = PlayerMongoDTO.class)))}),
            @ApiResponse(responseCode = "404", description = "There are no players introduced in the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "404", description = "Games not found in the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while retrieving the most losing player from the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<PlayerMongoDTO> getLoser() throws Exception {

        try {
            return new ResponseEntity<>(playerService.getMostLoser(), HttpStatus.OK);
        } catch (PlayerNotFoundException | GamesNotFoundException e ) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while retrieving the most losing player from the database", e.getCause());
        }
    }

    @Override
    @GetMapping(value = "/ranking/winner", produces = "application/json")
    @Operation(summary = "Get the most winner player", description = "Returns the most winner player stored in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Most winner player retrieved successfully", content = {@Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = PlayerMongoDTO.class)))}),
            @ApiResponse(responseCode = "404", description = "There are no players introduced in the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "404", description = "Games not found in the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error while retrieving the most winner player from the database", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Message.class))})})

    public ResponseEntity<PlayerMongoDTO> getWinner() throws Exception {

        try {
            return new ResponseEntity<>(playerService.getMostWinner(), HttpStatus.OK);
        } catch (PlayerNotFoundException | GamesNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Internal Server Error while retrieving the most winner player from the database", e.getCause());
        }
    }

}
