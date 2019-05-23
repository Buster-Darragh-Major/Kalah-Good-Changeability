package kalah.Bootstrap;

import com.qualitascorpus.testsupport.IO;
import kalah.Contracts.Factories.KalahRunnerFactory;
import kalah.Contracts.IO.InputInterpreter;
import kalah.Contracts.IO.Rendering.ConsoleOutputLookAndFeel;
import kalah.Contracts.IO.Rendering.OutputFormatter;
import kalah.Contracts.IO.UserCommunicator;
import kalah.Contracts.Rules.*;
import kalah.IO.EwansExampleInputInterpreter;
import kalah.IO.QualitusCorpusConsoleUserCommunicator;
import kalah.IO.Rendering.EwansExampleConsoleOutputLookAndFeel;
import kalah.IO.Rendering.TwoPlayerSingleStoreConsoleOutputFormatter;
import kalah.KalahRunner;
import kalah.Rules.*;

public class DefaultKalahRunnerFactory implements KalahRunnerFactory {

    private IO _io;

    public DefaultKalahRunnerFactory(IO io) {
        _io = io;
    }

    @Override
    public KalahRunner createKalahRunner() {
        RelativePlayerFinder relativePlayerFinder = new VanillaNextPlayerFinder();
        SeedSower seedSower = new CounterClockwiseSeedSower(relativePlayerFinder);
        OppositeHouseFinder oppositeHouseFinder = new VanillaOppositeHouseFinder();
        CaptureManager captureManager = new VanillaCaptureManager(relativePlayerFinder, oppositeHouseFinder);
        KalahRules kalahRules = new VanillaKalahRules(seedSower, relativePlayerFinder, captureManager);

        ConsoleOutputLookAndFeel consoleOutputLookAndFeel = new EwansExampleConsoleOutputLookAndFeel();
        OutputFormatter outputFormatter = new TwoPlayerSingleStoreConsoleOutputFormatter(consoleOutputLookAndFeel);
        InputInterpreter inputInterpreter = new EwansExampleInputInterpreter();
        UserCommunicator userCommunicator = new QualitusCorpusConsoleUserCommunicator(_io, outputFormatter, inputInterpreter);

        return new KalahRunner(kalahRules, userCommunicator);
    }
}
