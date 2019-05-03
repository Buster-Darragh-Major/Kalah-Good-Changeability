package kalah.Rules;

import kalah.Contracts.Model.Board;
import kalah.Contracts.Rules.KalahRules;
import kalah.Contracts.Rules.RelativePlayerFinder;
import kalah.Contracts.Rules.OppositeHouseFinder;
import kalah.Contracts.Rules.SeedSower;
import kalah.Exceptions.EmptyHouseException;
import kalah.Exceptions.HouseDoesntExistException;
import kalah.Model.House;
import kalah.Model.SeedStorage;
import kalah.Model.Store;

import java.util.List;

public class VanillaKalahRules implements KalahRules {

    private static final int STARTING_PLAYER = 1;
    private static final int HOUSE_CAPTURE_SEED_THRESHOLD = 0;
    private static final int GAME_OVER_SEED_THRESHOLD = 0;
    private static final int INVALID_TURN_SEED_THRESHOLD = 0;

    private SeedSower _seedSower;
    private RelativePlayerFinder _relativePlayerFinder;
    private OppositeHouseFinder _oppositeHouseFinder;

    public VanillaKalahRules(SeedSower seedSower, RelativePlayerFinder relativePlayerFinder, OppositeHouseFinder oppositeHouseFinder) {
        _seedSower = seedSower;
        _relativePlayerFinder = relativePlayerFinder;
        _oppositeHouseFinder = oppositeHouseFinder;
    }

    /**
     * Alters the board according to the standard Kalah rules given the player's turn and the chosen house index.
     * @param board
     * @param player
     * @param houseIndex
     * @return number of the player whose turn it is after this turn
     */
    @Override
    public int doTurn(Board board, int player, int houseIndex) {
        int houseListIndex = houseIndex - 1; // Convert UI index to list index

        List<House> playersHouses = board.getHousesForPlayer(player);
        if (houseListIndex >= playersHouses.size()) {
            throw new HouseDoesntExistException(String.format("House %d does not exist", houseIndex));
        }
        House house = playersHouses.get(houseListIndex);

        if (!(house.getSeeds() > INVALID_TURN_SEED_THRESHOLD)) {
            throw new EmptyHouseException(String.format("House %d for player %d is below valid turn threshold: %d",
                    houseIndex, player, INVALID_TURN_SEED_THRESHOLD));
        }

        SeedStorage terminalSeedStorer = _seedSower.sowSeeds(board, house);
        if ((terminalSeedStorer instanceof House) && (isACapture((House) terminalSeedStorer, player, board))) {
            doCapture((House) terminalSeedStorer, board);
        } else if ((terminalSeedStorer instanceof Store) && (terminalSeedStorer.getPlayer() == player)) {
            // If we terminate on the players store it's their turn again.
            return player;
        }
        return _relativePlayerFinder.findNextPlayer(board, player);
    }

    /**
     * Returns a boolean value indicating whether the Kalah game is over given which player's turn it is.
     * @param board
     * @param player
     * @return is game over
     */
    @Override
    public boolean isGameOver(Board board, int player) {
        for (House house : board.getHousesForPlayer(player)) {
            if (house.getSeeds() > GAME_OVER_SEED_THRESHOLD) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns the default Kalah rules starting player which is player 1.
     * @return 1
     */
    @Override
    public int startingPlayer() {
        return STARTING_PLAYER;
    }

    private boolean isACapture(House house, int player, Board board) {
        return house.getPlayer() == player
                && house.getSeeds() == HOUSE_CAPTURE_SEED_THRESHOLD + 1 // If it's 1 then it would have been 0 before the turn
                && _oppositeHouseFinder.findOppositeHouse(
                        board, house, _relativePlayerFinder.findCapturedPlayer(board, player)).getSeeds() != 0;
    }

    private void doCapture(House house, Board board) {
        int seedsToCapture = house.getSeeds();
        house.decrement(house.getSeeds());

        House oppositeHouse = _oppositeHouseFinder.findOppositeHouse(
                board, house, _relativePlayerFinder.findCapturedPlayer(board, house.getPlayer()));
        seedsToCapture += oppositeHouse.getSeeds();
        oppositeHouse.decrement(oppositeHouse.getSeeds());
        (board.getStoresForPlayer(house.getPlayer()).get(0)).increment(seedsToCapture);
    }
}
