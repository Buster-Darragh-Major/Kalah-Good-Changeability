package kalah.IO;

import kalah.Contracts.IO.InputInterpreter;

public class EwansExampleInputInterpreter implements InputInterpreter {

    private static final String QUIT_SYMBOL = "q";

    @Override
    public boolean isQuit(String str) {
        return str.equals(QUIT_SYMBOL);
    }

    @Override
    public int asNumber(String str) {
        return Integer.parseInt(str);
    }
}
