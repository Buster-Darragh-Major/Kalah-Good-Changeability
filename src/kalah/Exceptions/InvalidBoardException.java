package kalah.Exceptions;

public class InvalidBoardException extends IllegalArgumentException {

    public InvalidBoardException() {
        super();
    }

    public InvalidBoardException(String message) {
        super(message);
    }
}
