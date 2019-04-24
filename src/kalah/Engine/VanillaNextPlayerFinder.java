package kalah.Engine;

import kalah.Contracts.Board;
import kalah.Contracts.NextPlayerFinder;

public class VanillaNextPlayerFinder implements NextPlayerFinder {

    public int findNextPlayer(Board board, int player) {
        return board.getNumberOfPlayers() < ++player ? 1 : player;
    }

}
