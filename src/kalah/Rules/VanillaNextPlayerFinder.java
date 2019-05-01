package kalah.Rules;

import kalah.Contracts.Model.Board;
import kalah.Contracts.Rules.NextPlayerFinder;

public class VanillaNextPlayerFinder implements NextPlayerFinder {

    public int findNextPlayer(Board board, int player) {
        return board.getNumberOfPlayers() < ++player ? 1 : player;
    }

}
