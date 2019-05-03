package kalah.Bootstrap;

import kalah.Contracts.Factories.KalahRulesFactory;
import kalah.Contracts.Rules.*;
import kalah.Rules.*;

public class DefaultKalahRulesFactory implements KalahRulesFactory {

    @Override
    public KalahRules createKalahRules() {
        RelativePlayerFinder relativePlayerFinder = new VanillaNextPlayerFinder();
        SeedSower seedSower = new CounterClockwiseSeedSower(relativePlayerFinder);
        OppositeHouseFinder oppositeHouseFinder = new VanillaOppositeHouseFinder();
        CaptureManager captureManager = new VanillaCaptureManager(relativePlayerFinder, oppositeHouseFinder);
        return new VanillaKalahRules(seedSower, relativePlayerFinder, captureManager);
    }
}
