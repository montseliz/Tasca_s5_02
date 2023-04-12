package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.tools.Helpers.obtainWinPercentage;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {

    @Schema(description = "Identifier of the player", example = "1")
    private long player_id;

    @Schema(description = "Username of the player", example = "Montse")
    private String name;

    /**
     * L'atribut s'inicialitza automàticament (amb l'hora i data locals) en l'instant en què es crea una nova instància de PlayerDTO,
     * gràcies a l'anotació @CreationTimestamp.
     */
    @Schema(description = "Registration date of the player", example = "2023-04-10 18:46:38.227499")
    private LocalDateTime registration;

    @Schema(description = "List of games played by the player")
    private Set<GameDTO> gamesHistory = new HashSet<>();

    @Schema(description = "Player's winning percentage")
    private double winPercentage;

    public PlayerDTO(String name) {
        this.name = name;
    }

    public void addGamesHistory(GameDTO gameDTO) {
        gamesHistory.add(gameDTO);
    }

    public double generateWinPercentage() {
        return obtainWinPercentage(this);
    }
}
