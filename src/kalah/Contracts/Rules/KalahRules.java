package kalah.Contracts.Rules;

import kalah.Contracts.Model.Board;

public interface KalahRules {

    /**
     * Manipulates the board object argument given a certain implementations rule-set and the house index of the
     * player selected to "do a turn" on. Returns the player who's turn it is after turn terminates.
     * @param board
     * @param player
     * @param houseIndex
     * @return Player's identifier for who's turn it is after turn ends
     */
    int doTurn(Board board, int player, int houseIndex);

    /**
     * Checks whether game is over given a players turn and the state of the board.
     * @param board
     * @param player
     * @return true/false indicating game over
     */
    boolean isGameOver(Board board, int player);

    /**
     * Returns the player which should start the game in Kalah according to the implementation.
     * @return staring player
     */
    int startingPlayer();
}
