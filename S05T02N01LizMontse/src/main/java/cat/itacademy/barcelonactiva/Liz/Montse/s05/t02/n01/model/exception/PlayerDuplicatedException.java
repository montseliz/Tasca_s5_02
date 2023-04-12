package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.exception;

public class PlayerDuplicatedException extends RuntimeException {

    public PlayerDuplicatedException(String message) {
        super(message);
    }
}