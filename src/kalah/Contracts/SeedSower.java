package kalah.Contracts;

import kalah.Model.House;
import kalah.Model.SeedStorage;

public interface SeedSower {

    /**
     * Will sow seeds across a game board according to an implementations strategy. Returns the Incrementable that the
     * strategy terminated on.
     * @param board
     * @param house
     * @return
     */
    SeedStorage sowSeeds(Board board, House house);
}
