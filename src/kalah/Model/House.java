package kalah.Model;

import kalah.Contracts.SeedManipulation.Decrementable;
import kalah.Contracts.SeedManipulation.Incrementable;

public class House extends SeedStorage implements Incrementable, Decrementable {

    public House (int player, int index, int noSeeds) {
        super(player, index, noSeeds);
    }

    @Override
    public void decrement() {
        _noSeeds--;
    }

    @Override
    public void decrement(int numToDecrement) {
        _noSeeds -= numToDecrement;
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
