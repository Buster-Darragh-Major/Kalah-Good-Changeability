package kalah.Engine;

import kalah.Contracts.Board;
import kalah.Contracts.KalahRules;
import kalah.Exceptions.EmptyHouseException;
import kalah.Model.House;

import java.util.List;

public class VanillaKalahRules implements KalahRules {

    @Override
    public int doTurn(Board board, int player, int houseIndex) {
        int houseListIndex = houseIndex - 1; // Convert UI index to list index

        List<House> playersHouses = board.getHousesForPlayer(player);
        House house = playersHouses.get(houseListIndex);

        if (!(house.getSeeds() > 0)) {
            throw new EmptyHouseException(String.format("House %d for player %d is empty", houseIndex, player));
        }

        int seedsToDisperse = house.getSeeds();
        house.decrement(house.getSeeds());

        // TODO must increment house index on pass into recursive function
        return 0;
    }


}
