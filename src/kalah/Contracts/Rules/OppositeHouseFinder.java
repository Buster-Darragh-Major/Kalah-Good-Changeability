package kalah.Contracts.Rules;

import kalah.Contracts.Model.Board;
import kalah.Model.House;

public interface OppositeHouseFinder {

    House findOppositeHouse(Board board, House playerOneHouse, int playerTwo);
}
