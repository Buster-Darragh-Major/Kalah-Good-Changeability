package kalah.Contracts.Rules;

import kalah.Contracts.Model.Board;

public interface RelativePlayerFinder {

    /**
     * Finds the player whose turn it should be after the turn of the player argument given.
     * @param board
     * @param player
     * @return player to go next
     */
    int findNextPlayer(Board board, int player);

    /**
     * Finds the player that the given player argument should capture given a board setup.
     * @param board
     * @param player
     * @return player to capture
     */
    int findCapturedPlayer(Board board, int player);
}
