package kalah.IO;

import kalah.Contracts.IO.InputInterpreter;

public class EwansExampleInputInterpreter implements InputInterpreter {

    private static final String QUIT_SYMBOL = "q";

    /**
     * Checks if the input from user is the quit symbol.
     * @param str
     * @return is quit
     */
    @Override
    public boolean isQuit(String str) {
        return str.equals(QUIT_SYMBOL);
    }

    /**
     * transforms the inout from the user to a number. Throws unchecked NumberFormatException
     * @param str
     * @return str as number
     */
    @Override
    public int asNumber(String str) {
        return Integer.parseInt(str);
    }
}
