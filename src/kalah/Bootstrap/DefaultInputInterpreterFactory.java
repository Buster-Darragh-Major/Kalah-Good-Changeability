package kalah.Bootstrap;

import kalah.Contracts.Factories.InputInterpreterFactory;
import kalah.Contracts.IO.InputInterpreter;
import kalah.IO.EwansExampleInputInterpreter;

public class DefaultInputInterpreterFactory implements InputInterpreterFactory {
    @Override
    public InputInterpreter createInputInterpreter() {
        return new EwansExampleInputInterpreter();
    }
}
