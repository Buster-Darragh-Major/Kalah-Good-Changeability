package kalah.IO;

import kalah.Contracts.IO.InputInterpreter;

public class UserInput {

    private boolean _isQuit = false;
    private int _number = -1;

    public UserInput(String input, InputInterpreter inputInterpreter) {
        if (inputInterpreter.isQuit(input)) {
            _isQuit = true;
        } else {
            _number = inputInterpreter.asNumber(input);
        }
    }

    public boolean isQuit() {
        return _isQuit;
    }

    public int getNumber() {
        return _number;
    }
}
