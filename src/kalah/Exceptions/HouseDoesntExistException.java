package kalah.Exceptions;

public class HouseDoesntExistException extends IllegalArgumentException {

    public HouseDoesntExistException() {
        super();
    }

    public HouseDoesntExistException(String msg) {
        super(msg);
    }
}
