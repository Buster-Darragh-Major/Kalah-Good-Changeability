package kalah.Exceptions;

/**
 * Exception for throwing when there is no such house specified by the user.
 */
public class HouseDoesntExistException extends IllegalArgumentException {

    public HouseDoesntExistException() {
        super();
    }

    public HouseDoesntExistException(String msg) {
        super(msg);
    }
}
