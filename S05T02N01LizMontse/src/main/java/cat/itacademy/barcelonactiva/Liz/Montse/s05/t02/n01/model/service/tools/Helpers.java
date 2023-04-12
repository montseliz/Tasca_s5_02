package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.service.tools;

import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql.GameDTO;
import cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.dto.mysql.PlayerDTO;

import java.text.DecimalFormat;
import java.util.Random;


public class Helpers {

    /**
     * Mètode encarregat de calcular el percentatge de victòries del PlayerDTO.
     */
    public static double obtainWinPercentage(PlayerDTO playerDTO) {
        if (playerDTO.getGamesHistory().isEmpty()) {
            throw new ArithmeticException("Cannot calculate a player's winning percentage with no games played");
        } else {
            long totalGames = playerDTO.getGamesHistory().size();
            long totalWins = playerDTO.getGamesHistory().stream().filter(g -> g.getResult() == GameDTO.ResultGame.WINNER).count();
            double winPercentage = ((double) totalWins / totalGames) * 100.0d;
            DecimalFormat df = new DecimalFormat("#.##");
            return Double.parseDouble(df.format(winPercentage));
        }
    }

    /**
     * Mètode per generar una tirada de dau amb un valor aleatori.
     */
    public static short rollDice() {
        Random random = new Random();
        return (short) (random.nextInt(6) + 1);
    }

    /**
     * Mètode per obtenir el resultat de la partida i assignar l'enum de GameDTO en cas que es guanyi o perdi.
     */
    public static GameDTO.ResultGame obtainResult(short dice1, short dice2) {
        if (dice1 + dice2 == 7) {
            return GameDTO.ResultGame.WINNER;
        } else {
            return GameDTO.ResultGame.LOSER;
        }
    }
}
