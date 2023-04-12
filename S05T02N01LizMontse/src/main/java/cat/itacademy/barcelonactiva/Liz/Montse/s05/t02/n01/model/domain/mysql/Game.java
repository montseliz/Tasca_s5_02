package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.domain.mysql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private long game_id;

    @Column (name = "dice1", nullable = false)
    private short dice1;

    @Column (name = "dice2", nullable = false)
    private short dice2;

    @Enumerated(EnumType.STRING)
    @Column(name = "result", nullable = false, columnDefinition = "ENUM('WINNER', 'LOSER')")
    private ResultGame result;

    /**
     * La propietat "FetchType.LAZY" significa que l'objecte Player no es recuperarà automàticament quan es recuperi l'entitat Game.
     * En el seu lloc, es recuperarà només quan sigui necessari, és a dir, quan es cridi explícitament al getter de l'objecte Player.
     * L'anotació @JoinColumn s'utilitza per especificar la columna en la taula de l'entitat Game que s'utilitzarà per emmagatzemar
     * l'ID de l'entitat Player relacionada.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    public enum ResultGame {
        WINNER, LOSER
    }
}
