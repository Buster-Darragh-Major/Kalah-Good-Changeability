package kalah.Bootstrap;

import kalah.Contracts.Factories.OutputFormatterFactory;
import kalah.Contracts.IO.Rendering.OutputFormatter;
import kalah.IO.Rendering.Assignment5VerticalLayout;
import kalah.IO.Rendering.EwansExampleConsoleOutputLookAndFeel;
import kalah.IO.Rendering.TwoPlayerSingleStoreConsoleOutputFormatter;

public class DefaultOutputFormatterFactory implements OutputFormatterFactory {
    @Override
    public OutputFormatter createOutputFormatter() {
        return new Assignment5VerticalLayout(
                new EwansExampleConsoleOutputLookAndFeel());
    }
}
