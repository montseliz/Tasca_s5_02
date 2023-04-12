package cat.itacademy.barcelonactiva.Liz.Montse.s05.t02.n01.model.exception;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(String message) {
        super(message);
    }
}
