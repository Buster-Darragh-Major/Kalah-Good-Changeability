import kalah.Contracts.Model.Board;
import kalah.Model.HashMapBoard;
import org.junit.Assert;
import org.junit.Test;

public class HashmapBoardTests {

    @Test
    public void testIsATie() {
        Board board = new HashMapBoard.Builder()
                .seedsPerStore(0)
                .seedsPerHouse(0)
                .numberOfPlayers(2)
                .housesPerPlayer(6)
                .storesPerPlayer(1)
                .build();

        board.getStoresForPlayer(2).get(0).increment(24);
        board.getStoresForPlayer(1).get(0).increment(21);
        board.getHousesForPlayer(1).get(1).increment(1);
        board.getHousesForPlayer(1).get(3).increment(1);
        board.getHousesForPlayer(1).get(5).increment(1);

        Assert.assertEquals(-1, board.getWinningPlayer());
    }
}
