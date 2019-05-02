package kalah.Bootstrap;

import kalah.Contracts.Factories.OutputFormatterFactory;
import kalah.Contracts.Rendering.OutputFormatter;
import kalah.Rendering.EwansExampleConsoleOutputLookAndFeel;
import kalah.Rendering.TwoPlayerSingleStoreConsoleOutputFormatter;

public class DefaultOutputFormatterFactory implements OutputFormatterFactory {
    @Override
    public OutputFormatter createOutputFormatter() {
        return new TwoPlayerSingleStoreConsoleOutputFormatter(
                new EwansExampleConsoleOutputLookAndFeel());
    }
}
