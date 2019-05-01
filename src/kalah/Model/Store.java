package kalah.Model;

import kalah.Contracts.Model.SeedManipulation.Incrementable;

public class Store extends SeedStorage implements Incrementable {

    public Store (int player, int index, int noSeeds) {
        super(player, index, noSeeds);
    }

    @Override
    public void increment() {
        _noSeeds++;
    }

    @Override
    public void increment(int numToIncrement) {
        _noSeeds += numToIncrement;
    }
}
