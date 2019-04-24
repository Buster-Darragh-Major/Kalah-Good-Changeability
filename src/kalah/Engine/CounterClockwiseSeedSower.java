package kalah.Engine;

import kalah.Contracts.Board;
import kalah.Contracts.SeedManipulation.Incrementable;
import kalah.Contracts.SeedSower;
import kalah.Model.SeedStorage;

import java.util.ArrayList;
import java.util.List;

public class CounterClockwiseSeedSower implements SeedSower {

    @Override
    public SeedStorage sowSeedsAmongContainers(int numberOfSeeds, Board board, int player, int startingIndex) {
        if (board == null) throw new NullPointerException("Board can't be null");
        if (player <= 0) throw new IllegalArgumentException(String.format("Player index is less than 1, found %d", player));

        List<Incrementable> seedContainers = getPlayersContainers(board, player);
        return recurse(numberOfSeeds, board, seedContainers, player, startingIndex, null);
    }

    private SeedStorage recurse(int seedsLeft, Board board, List<Incrementable> seedContainers, int player, int containerIndex, Incrementable currentTerminal) {
        while (seedsLeft > 0) {
            seedContainers.get(containerIndex).increment();
            currentTerminal = seedContainers.get(containerIndex);
            seedsLeft--;
            if (++containerIndex >= seedContainers.size()) {
                player = getNextPlayer(board, player);
                List<Incrementable> nextPlayersContainers = getPlayersContainers(board, player);
                return recurse(seedsLeft, board, nextPlayersContainers, player, 0, currentTerminal);
            }
        }
        return (SeedStorage) currentTerminal;
    }

    private int getNextPlayer(Board board, int player) {
        return board.getNumberOfPlayers() < ++player ? 1 : player;
    }

    private List<Incrementable> getPlayersContainers(Board board, int player) {
        List<Incrementable> returnList = new ArrayList<>();
        returnList.addAll(board.getHousesForPlayer(player));
        returnList.addAll(board.getStoresForPlayer(player));
        return returnList;
    }
}
