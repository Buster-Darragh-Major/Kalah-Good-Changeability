package kalah.Contracts.Rules;

import kalah.Contracts.Model.Board;

public interface NextPlayerFinder {

    int findNextPlayer(Board board, int player);
}
