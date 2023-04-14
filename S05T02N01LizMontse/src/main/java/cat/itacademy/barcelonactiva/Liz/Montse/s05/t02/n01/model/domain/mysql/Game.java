package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.domain.mysql;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.tools.Helpers.obtainResult;
import static cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.tools.Helpers.rollDice;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    @Schema(description = "Identifier of the game", example = "1")
    private long game_id;

    @Column(name = "dice1", nullable = false)
    @Schema(description = "Value of first dice", example = "4")
    private short dice1;

    @Column(name = "dice2", nullable = false)
    @Schema(description = "Value of second dice", example = "3")
    private short dice2;

    @Enumerated(EnumType.STRING)
    @Column(name = "result", nullable = false, columnDefinition = "ENUM('WINNER', 'LOSER')")
    @Schema(description = "Result of the game", example = "WINNER")
    private ResultGame result;

    /**
     * La propietat "FetchType.LAZY" significa que l'objecte Player no es recuperarà automàticament quan es recuperi l'entitat Game.
     * En el seu lloc, es recuperarà només quan sigui necessari, és a dir, quan es cridi explícitament el getter de l'objecte Player.
     * L'anotació @JoinColumn s'utilitza per especificar la columna en la taula de l'entitat Game que s'utilitzarà per emmagatzemar
     * l'ID de l'entitat Player relacionada.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    @Schema(description = "Player playing the game", example = "1")
    private Player player;

    public enum ResultGame {
        WINNER, LOSER
    }

    public Game(Player player) {
        this.player= player;
        this.dice1 = rollDice();
        this.dice2 = rollDice();
        this.result = obtainResult(dice1, dice2);
    }

    @Override
    public String toString() {
        return "Game{" +
                "game_id=" + game_id +
                ", dice1=" + dice1 +
                ", dice2=" + dice2 +
                ", result=" + result +
                ", player=" + (player == null ? "null" : player.getPlayer_id()) +
                '}';
    }
}