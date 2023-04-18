package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.mongodb.tools;

import java.util.Random;

public class HelpersMongoDB {

    public static short rollDice() {
        Random random = new Random();
        return (short) (random.nextInt(6) + 1);
    }

    public static String obtainResult(short dice1, short dice2) {
        if (dice1 + dice2 == 7) {
            return "WINNER";
        } else {
            return "LOSER";
        }
    }
}
