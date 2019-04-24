package kalah.Exceptions;

public class EmptyHouseException extends IllegalArgumentException {

    public EmptyHouseException() {
        super();
    }

    public EmptyHouseException(String msg) {
        super(msg);
    }
}
