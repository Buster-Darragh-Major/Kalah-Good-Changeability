package kalah.Contracts;

import kalah.Contracts.SeedManipulation.Incrementable;
import kalah.Model.SeedStorage;

import java.util.List;

public interface SeedSower {

    /**
     * Will sow seeds across a game board according to an implementations strategy. Returns the Incrementable that the
     * strategy terminated on.
     * @param numberOfSeeds
     * @param board
     * @param player
     * @param startingIndex
     * @return
     */
    SeedStorage sowSeeds(int numberOfSeeds, Board board, int player, int startingIndex);
}
