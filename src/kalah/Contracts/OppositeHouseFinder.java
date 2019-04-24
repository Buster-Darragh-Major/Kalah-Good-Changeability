package kalah.Contracts;

import kalah.Model.House;

public interface OppositeHouseFinder {

    House findOppositeHouse(Board board, House playerOneHouse, int playerTwo);
}
