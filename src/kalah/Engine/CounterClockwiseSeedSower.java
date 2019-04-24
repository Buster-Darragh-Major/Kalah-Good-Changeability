package kalah.Engine;

import kalah.Contracts.Board;
import kalah.Contracts.NextPlayerFinder;
import kalah.Contracts.SeedManipulation.Incrementable;
import kalah.Contracts.SeedSower;
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

    @Override
    public SeedStorage sowSeeds(Board board, House house) {
        if (board == null) throw new NullPointerException("Board can't be null");

        int numberOfSeeds = house.getSeeds();
        house.decrement(house.getSeeds());

        List<Incrementable> seedContainers = getPlayersContainers(board, house.getPlayer());
        return recurse(house.getPlayer(), numberOfSeeds, board, seedContainers, house.getPlayer(), house.getIndex() + 1, house);
    }

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
