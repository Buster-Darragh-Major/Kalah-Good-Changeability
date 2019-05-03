package kalah.Rules;

import kalah.Contracts.Model.Board;
import kalah.Contracts.Rules.CaptureManager;
import kalah.Contracts.Rules.OppositeHouseFinder;
import kalah.Contracts.Rules.RelativePlayerFinder;
import kalah.Model.House;

public class VanillaCaptureManager implements CaptureManager {

    private static final int HOUSE_CAPTURE_SEED_THRESHOLD = 0;

    private final RelativePlayerFinder _relativePlayerFinder;
    private final OppositeHouseFinder _oppositeHouseFinder;

    public VanillaCaptureManager(RelativePlayerFinder relativePlayerFinder, OppositeHouseFinder oppositeHouseFinder) {
        _relativePlayerFinder = relativePlayerFinder;
        _oppositeHouseFinder = oppositeHouseFinder;
    }

    /**
     * Determines whether the given house on the board for the given player warrants a house capture. Implemented as
     * whether the given house has 0 seeds and the opposite house for this player has >0 seeds. The notion os 'opposite'
     * is decided by the injected OppositeHouseFinder and RelativePlayerFinder implementations.
     * @param house
     * @param player
     * @param board
     * @return boolean indicating whether this house indicates a capture
     */
    @Override
    public boolean isACapture(House house, int player, Board board) {
        return house.getPlayer() == player
                && house.getSeeds() == HOUSE_CAPTURE_SEED_THRESHOLD + 1 // If it's 1 then it would have been 0 before the turn
                && _oppositeHouseFinder.findOppositeHouse(
                board, house, _relativePlayerFinder.findCapturedPlayer(board, player)).getSeeds() != 0;
    }

    /**
     * Empties the seeds from the house and the opposite house from this house. This opposite house is determined by the
     * injected OppositeHouseFinder and RelativePlayerFinder implementations. Also fills the house's player's store with
     * seeds.
     * @param house
     * @param board
     */
    @Override
    public void doCapture(House house, Board board) {
        int seedsToCapture = house.getSeeds();
        house.decrement(house.getSeeds());

        House oppositeHouse = _oppositeHouseFinder.findOppositeHouse(
                board, house, _relativePlayerFinder.findCapturedPlayer(board, house.getPlayer()));
        seedsToCapture += oppositeHouse.getSeeds();
        oppositeHouse.decrement(oppositeHouse.getSeeds());
        (board.getStoresForPlayer(house.getPlayer()).get(0)).increment(seedsToCapture);
    }
}
