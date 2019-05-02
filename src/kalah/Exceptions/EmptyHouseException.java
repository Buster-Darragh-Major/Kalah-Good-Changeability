package kalah.Exceptions;

/**
 * Exception for throwing when the specified house by the user is empty
 */
public class EmptyHouseException extends IllegalArgumentException {

    public EmptyHouseException() {
        super();
    }

    public EmptyHouseException(String msg) {
        super(msg);
    }
}
