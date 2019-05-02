package kalah.Rules;

import kalah.Contracts.Model.Board;
import kalah.Contracts.Rules.NextPlayerFinder;
import kalah.Contracts.Model.SeedManipulation.Incrementable;
import kalah.Contracts.Rules.SeedSower;
import kalah.Model.House;
import kalah.Model.SeedStorage;
import kalah.Model.Store;

import java.util.ArrayList;
import java.util.List;

public class CounterClockwiseSeedSower implements SeedSower {

    private NextPlayerFinder _nextPlayerFinder;

    public CounterClockwiseSeedSower(NextPlayerFinder nextPlayerFinder) {
        _nextPlayerFinder = nextPlayerFinder;
    }

    /**
     * Sows the seeds of a Kalah Board Counter Clockwise. The board object passed here will have its seeds counts
     * increments given the passed starting house.
     * @param board
     * @param house
     * @return terminal spot on the board
     */
    @Override
    public SeedStorage sowSeeds(Board board, House house) {
        if (board == null) throw new NullPointerException("Board can't be null");

        int numberOfSeeds = house.getSeeds();
        house.decrement(house.getSeeds());

        List<Incrementable> seedContainers = getPlayersContainers(board, house.getPlayer());
        return recurse(house.getPlayer(), numberOfSeeds, board, seedContainers, house.getPlayer(), house.getIndex() + 1, house);
    }

    /**
     * This recursive method loops though the given list of implementables until it runs out, if there are still seeds
     * left to sow then it recurses with the incrementable list of the next player in the game determined by the
     * NextPlayerFinder implementation.
     */
    private SeedStorage recurse(int originalPlayer, int seedsLeft, Board board, List<Incrementable> seedContainers, int player, int containerIndex, Incrementable currentTerminal) {
        while (seedsLeft > 0) {
            if (!isOpponentsStore(seedContainers.get(containerIndex), originalPlayer)) {
                seedContainers.get(containerIndex).increment();
                currentTerminal = seedContainers.get(containerIndex);
                seedsLeft--;
            }
            if (++containerIndex >= seedContainers.size()) {
                player = _nextPlayerFinder.findNextPlayer(board, player);
                List<Incrementable> nextPlayersContainers = getPlayersContainers(board, player);
                return recurse(originalPlayer, seedsLeft, board, nextPlayersContainers, player, 0, currentTerminal);
            }
        }
        return (SeedStorage) currentTerminal;
    }

    private boolean isOpponentsStore(Incrementable incrementable, int originalPlayer) {
        return incrementable instanceof Store && ((SeedStorage) incrementable).getPlayer() != originalPlayer;
    }

    private List<Incrementable> getPlayersContainers(Board board, int player) {
        List<Incrementable> returnList = new ArrayList<>();
        returnList.addAll(board.getHousesForPlayer(player));
        returnList.addAll(board.getStoresForPlayer(player));
        return returnList;
    }
}
