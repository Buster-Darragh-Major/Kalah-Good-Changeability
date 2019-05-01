package kalah.Rules;

import kalah.Contracts.Model.Board;
import kalah.Contracts.Rules.OppositeHouseFinder;
import kalah.Model.House;

public class VanillaOppositeHouseFinder implements OppositeHouseFinder {


    @Override
    public House findOppositeHouse(Board board, House playerOneHouse, int playerTwo) {
        return board.getHousesForPlayer(playerTwo).get(
                board.getHousesForPlayer(playerTwo).size() - 1 - playerOneHouse.getIndex());
    }
}
