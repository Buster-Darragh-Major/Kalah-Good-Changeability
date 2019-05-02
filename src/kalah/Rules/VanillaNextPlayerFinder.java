package kalah.Rules;

import kalah.Contracts.Model.Board;
import kalah.Contracts.Rules.NextPlayerFinder;

public class VanillaNextPlayerFinder implements NextPlayerFinder {

    /**
     * Returns one player higher than that of the current player in the game. If it is the maximum numbered player
     * (e.g. player 5 when there are only 4 players) then 1 will be returned.
     * @param board
     * @param player
     * @return next player number incremented
     */
    public int findNextPlayer(Board board, int player) {
        return board.getNumberOfPlayers() < ++player ? 1 : player;
    }

}
