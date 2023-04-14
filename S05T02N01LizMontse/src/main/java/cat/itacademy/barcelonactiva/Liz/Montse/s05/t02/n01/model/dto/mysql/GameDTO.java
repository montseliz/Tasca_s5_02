package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
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

    @Schema(description = "Name of the player", example = "montse")
    private String namePlayer;

    public enum ResultGame {
        WINNER, LOSER
    }

}
