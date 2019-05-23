package kalah.Bootstrap;

import kalah.Contracts.Factories.OutputFormatterFactory;
import kalah.Contracts.IO.Rendering.OutputFormatter;
import kalah.IO.Rendering.Assignment5VerticalLayoutOutputFormatter;
import kalah.IO.Rendering.EwansExampleConsoleOutputLookAndFeel;

public class DefaultOutputFormatterFactory implements OutputFormatterFactory {
    @Override
    public OutputFormatter createOutputFormatter() {
        return new Assignment5VerticalLayoutOutputFormatter(
                new EwansExampleConsoleOutputLookAndFeel());
    }
}
