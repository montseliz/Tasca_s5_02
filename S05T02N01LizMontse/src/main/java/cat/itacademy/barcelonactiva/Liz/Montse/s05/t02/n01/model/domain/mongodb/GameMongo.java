package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.domain.mongodb;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import static cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.mongodb.tools.HelpersMongoDB.obtainResult;
import static cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.mongodb.tools.HelpersMongoDB.rollDice;

@Data
@AllArgsConstructor
public class GameMongo {

    @Field(name = "dice1")
    @Schema(description = "Value of first dice", example = "4")
    private short dice1;

    @Field(name = "dice2")
    @Schema(description = "Value of second dice", example = "3")
    private short dice2;

    @Field(name = "result")
    @Schema(description = "Result of the game", example = "WINNER")
    private String result;

    public GameMongo() {
        this.dice1 = rollDice();
        this.dice2 = rollDice();
        this.result = obtainResult(dice1, dice2);
    }

}

