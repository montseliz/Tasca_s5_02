package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mongodb;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameMongoDTO {

    @Schema(description = "Value of first dice", example = "4")
    private short dice1;

    @Schema(description = "Value of second dice", example = "3")
    private short dice2;

    @Schema(description = "Result of the game", example = "WINNER")
    private String result;

    @Schema(description = "Name of the player", example = "montse")
    private String namePlayer;

}