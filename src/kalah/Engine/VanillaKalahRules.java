package kalah.Engine;

import kalah.Contracts.*;
import kalah.Exceptions.EmptyHouseException;
import kalah.Model.House;
import kalah.Model.SeedStorage;
import kalah.Model.Store;

import java.util.List;

public class VanillaKalahRules implements KalahRules {

    private SeedSower _seedSower;
    private NextPlayerFinder _nextPlayerFinder;
    private OppositeHouseFinder _oppositeHouseFinder;

    public VanillaKalahRules(SeedSower seedSower, NextPlayerFinder nextPlayerFinder, OppositeHouseFinder oppositeHouseFinder) {
        _seedSower = seedSower;
        _nextPlayerFinder = nextPlayerFinder;
        _oppositeHouseFinder = oppositeHouseFinder;
    }

    @Override
    public int doTurn(Board board, int player, int houseIndex) {
        int houseListIndex = houseIndex - 1; // Convert UI index to list index

        List<House> playersHouses = board.getHousesForPlayer(player);
        House house = playersHouses.get(houseListIndex);

        if (!(house.getSeeds() > 0)) {
            throw new EmptyHouseException(String.format("House %d for player %d is empty", houseIndex, player));
        }

        SeedStorage terminalSeedStorer = _seedSower.sowSeeds(board, house);
        if ((terminalSeedStorer instanceof House) && (isACapture((House) terminalSeedStorer, player, board))) {
            doCapture((House) terminalSeedStorer, board);
        } else if ((terminalSeedStorer instanceof Store) && (terminalSeedStorer.getPlayer() == player)) {
            // If we terminate on the players store it's their turn again.
            return player;
        }
        return _nextPlayerFinder.findNextPlayer(board, player);
    }

    @Override
    public boolean isGameOver(Board board, int player) {
        return false; // TODO:
    }

    private boolean isACapture(House house, int player, Board board) {
        return house.getPlayer() == player
                && house.getSeeds() == 1 // If it's 1 then it would have been 0 before the turn
                && _oppositeHouseFinder.findOppositeHouse(
                        board, house, _nextPlayerFinder.findNextPlayer(board, player)).getSeeds() != 0;
    }

    private void doCapture(House house, Board board) {
        int seedsToCapture = house.getSeeds();
        house.decrement(house.getSeeds());

        House oppositeHouse = _oppositeHouseFinder.findOppositeHouse(
                board, house, _nextPlayerFinder.findNextPlayer(board, house.getPlayer()));
        seedsToCapture += oppositeHouse.getSeeds();
        oppositeHouse.decrement(oppositeHouse.getSeeds());
        (board.getStoresForPlayer(house.getPlayer()).get(0)).increment(seedsToCapture);
    }
}
