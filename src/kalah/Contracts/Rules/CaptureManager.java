package kalah.Contracts.Rules;

import kalah.Contracts.Model.Board;
import kalah.Model.House;

public interface CaptureManager {

    /**
     * Determines whether the given house on the board with player's turn warrants a capture.
     * @param house
     * @param player
     * @param board
     * @return  true/false indicating capture
     */
    boolean isACapture(House house, int player, Board board);

    /**
     * Alters the board by performing a capture given some implemented strategy.
     * @param house
     * @param board
     */
    void doCapture(House house, Board board);
}
