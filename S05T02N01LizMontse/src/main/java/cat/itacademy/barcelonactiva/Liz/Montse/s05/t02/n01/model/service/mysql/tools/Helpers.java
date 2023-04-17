package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.mysql.tools;

import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.domain.mysql.Game;
import java.util.Random;

public class Helpers {

    /**
     * Mètode per generar una tirada de dau amb un valor aleatori.
     */
    public static short rollDice() {
        Random random = new Random();
        return (short) (random.nextInt(6) + 1);
    }

    /**
     * Mètode per obtenir el resultat de la partida i assignar l'enum de Game en cas que es guanyi o perdi.
     */
    public static Game.ResultGame obtainResult(short dice1, short dice2) {
        if (dice1 + dice2 == 7) {
            return Game.ResultGame.WINNER;
        } else {
            return Game.ResultGame.LOSER;
        }
    }
}
