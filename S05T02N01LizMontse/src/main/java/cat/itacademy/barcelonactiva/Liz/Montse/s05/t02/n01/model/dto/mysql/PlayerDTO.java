package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {

    @Schema(description = "Identifier of the player", example = "1")
    private long player_id;

    @Schema(description = "Username of the player", example = "Montse")
    private String name;

    @Schema(description = "Registration date of the player", example = "2023-04-10 18:46:38.227499")
    private LocalDateTime registration;

    @Schema(description = "Player's winning percentage", example = "8.5")
    private double winPercentage;

    public PlayerDTO(String name) {
        this.name = name;
    }

}
