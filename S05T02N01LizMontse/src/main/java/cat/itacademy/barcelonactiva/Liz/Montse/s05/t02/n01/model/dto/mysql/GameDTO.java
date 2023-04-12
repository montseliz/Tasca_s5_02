package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import static cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.tools.Helpers.obtainResult;
import static cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.tools.Helpers.rollDice;

@Data
@NoArgsConstructor
public class GameDTO {

    @Schema(description = "Identifier of the game", example = "1")
    private long game_id;

    @Schema(description = "Value of first dice", example = "4")
    private short dice1;

    @Schema(description = "Value of second dice", example = "3")
    private short dice2;

    @Schema(description = "Result of the game", example = "WINNER")
    private ResultGame result;

    /**
     *  L'anotació @JsonIgnore evitarà que es mostri el PlayerDTO en el JSON d'un GameDTO.
     *  També evitarà que es produeixi una referència circular que provoqui un Stack Overflow
     *  en el procés de serialització dels objectes DTO a JSON.
     *  Aquesta anotació indicarà a la biblioteca de serialització que el PlayerDTO no s'ha de serialitzar a JSON.
     */
    @Schema(description = "Player of the game")
    @JsonIgnore
    private PlayerDTO playerDTO;

    public GameDTO(PlayerDTO playerDTO) {
        this.playerDTO = playerDTO;
        this.dice1 = rollDice();
        this.dice2 = rollDice();
        this.result = obtainResult(dice1, dice2);
        updatePlayer();
    }

    public enum ResultGame {
        WINNER, LOSER
    }

    public void updatePlayer(){
        this.playerDTO.addGamesHistory(this);
        this.playerDTO.generateWinPercentage();
    }

}
