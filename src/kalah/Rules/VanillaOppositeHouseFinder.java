package kalah.Rules;

import kalah.Contracts.Model.Board;
import kalah.Contracts.Rules.OppositeHouseFinder;
import kalah.Model.House;

public class VanillaOppositeHouseFinder implements OppositeHouseFinder {

    /**
     * Given 2 players, this will find the 'opposite' house between them. This will be the house on the opposite side
     * of player 2's board, given player 1's house. For example, wil a board of 10 houses, player 2's house opposite
     * to player 1's house 3 will be 7.
     * @param board
     * @param playerOneHouse
     * @param playerTwo
     * @return
     */
    @Override
    public House findOppositeHouse(Board board, House playerOneHouse, int playerTwo) {
        return board.getHousesForPlayer(playerTwo).get(
                board.getHousesForPlayer(playerTwo).size() - 1 - playerOneHouse.getIndex());
    }
}
