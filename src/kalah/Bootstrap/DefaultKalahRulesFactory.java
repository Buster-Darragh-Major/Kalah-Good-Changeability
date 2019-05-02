package kalah.Bootstrap;

import kalah.Contracts.Factories.KalahRulesFactory;
import kalah.Contracts.Rules.KalahRules;
import kalah.Contracts.Rules.NextPlayerFinder;
import kalah.Contracts.Rules.OppositeHouseFinder;
import kalah.Contracts.Rules.SeedSower;
import kalah.Rules.CounterClockwiseSeedSower;
import kalah.Rules.VanillaKalahRules;
import kalah.Rules.VanillaNextPlayerFinder;
import kalah.Rules.VanillaOppositeHouseFinder;

public class DefaultKalahRulesFactory implements KalahRulesFactory {

    @Override
    public KalahRules createKalahRules() {
        NextPlayerFinder nextPlayerFinder = new VanillaNextPlayerFinder();
        SeedSower seedSower = new CounterClockwiseSeedSower(nextPlayerFinder);
        OppositeHouseFinder oppositeHouseFinder = new VanillaOppositeHouseFinder();
        return new VanillaKalahRules(seedSower, nextPlayerFinder, oppositeHouseFinder);
    }
}
