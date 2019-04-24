package kalah.Contracts;

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
}
