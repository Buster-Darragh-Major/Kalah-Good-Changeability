package kalah.Model;

import kalah.Contracts.SeedManipulation.Incrementable;

public class Store extends SeedStorage implements Incrementable {

    public Store (int noSeeds) {
        super(noSeeds);
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
