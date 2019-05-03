package kalah.Contracts.Rules;

import kalah.Contracts.Model.Board;
import kalah.Model.House;

public interface OppositeHouseFinder {

    /**
     * Finds the house deemed to be opposite to the playerOneHouse for playerTwo. Notion of 'opposite' decided by
     * implementation.
     * @param board
     * @param playerOneHouse
     * @param playerTwo
     * @return
     */
    House findOppositeHouse(Board board, House playerOneHouse, int playerTwo);
}
