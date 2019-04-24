import kalah.CLI.EwansExampleConsoleOutputLookAndFeel;
import kalah.CLI.TwoPlayerSingleStoreConsoleOutputFormatter;
import kalah.Contracts.Board;
import kalah.Contracts.OutputFormatter;
import kalah.Model.HashMapBoard;
import kalah.Model.House;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TwoPlayerSingleStoreConsoleOutputFormatterTests {

    private OutputFormatter twoPlayerSingleStoreConsoleOutputFormatter;
    private Board _board;

    @Before
    public void runBefore() {
        // For purposes of this test there's probably no need to mock the look and feel
        twoPlayerSingleStoreConsoleOutputFormatter = new TwoPlayerSingleStoreConsoleOutputFormatter(
                new EwansExampleConsoleOutputLookAndFeel());
        _board = new HashMapBoard.Builder()
                .numberOfPlayers(2)
                .housesPerPlayer(6)
                .seedsPerHouse(4)
                .seedsPerStore(0)
                .storesPerPlayer(1)
                .build();
    }

    @Test
    public void line1() {
        String output = twoPlayerSingleStoreConsoleOutputFormatter.formatOutput(_board);
        String[] lines = output.split("\n");

        Assert.assertEquals("+----+-------+-------+-------+-------+-------+-------+----+", lines[0]);
    }

    @Test
    public void line2() {
        String output = twoPlayerSingleStoreConsoleOutputFormatter.formatOutput(_board);
        String[] lines = output.split("\n");

        Assert.assertEquals("| P2 | 6[ 4] | 5[ 4] | 4[ 4] | 3[ 4] | 2[ 4] | 1[ 4] |  0 |", lines[1]);
    }

    @Test
    public void line3() {
        String output = twoPlayerSingleStoreConsoleOutputFormatter.formatOutput(_board);
        String[] lines = output.split("\n");

        Assert.assertEquals("|    |-------+-------+-------+-------+-------+-------|    |", lines[2]);
    }

    @Test
    public void line4() {
        String output = twoPlayerSingleStoreConsoleOutputFormatter.formatOutput(_board);
        String[] lines = output.split("\n");

        Assert.assertEquals("|  0 | 1[ 4] | 2[ 4] | 3[ 4] | 4[ 4] | 5[ 4] | 6[ 4] | P1 |", lines[3]);
    }

    @Test
    public void line5() {
        String output = twoPlayerSingleStoreConsoleOutputFormatter.formatOutput(_board);
        String[] lines = output.split("\n");

        Assert.assertEquals("+----+-------+-------+-------+-------+-------+-------+----+", lines[4]);
    }

    @Test
    public void zeroHouses() {
        Board trivialBoard = new HashMapBoard.Builder()
                .numberOfPlayers(2)
                .housesPerPlayer(0)
                .seedsPerHouse(4)
                .seedsPerStore(0)
                .storesPerPlayer(1)
                .build();

        String output = twoPlayerSingleStoreConsoleOutputFormatter.formatOutput(trivialBoard);

        Assert.assertEquals("+----+----+\n" +
                "| P2 |  0 |\n" +
                "|    |    |\n" +
                "|  0 | P1 |\n" +
                "+----+----+", output);
    }

    @Test
    public void exampleOnAssignmentBrief() {
        Board board = new HashMapBoard.Builder()
                .numberOfPlayers(2)
                .housesPerPlayer(6)
                .seedsPerHouse(4)
                .seedsPerStore(0)
                .storesPerPlayer(1)
                .build();

        /**
         * Setup to:
         * +----+-------+-------+-------+-------+-------+-------+----+
         * | P2 | 6[ 5] | 5[ 1] | 4[ 2] | 3[ 3] | 2[ 4] | 1[ 0] | 14 |
         * |    |-------+-------+-------+-------+-------+-------|    |
         * |  4 | 1[ 5] | 2[ 5] | 3[ 2] | 4[ 0] | 5[ 0] | 6[ 3] | P1 |
         * +----+-------+-------+-------+-------+-------+-------+----+
         */

        List<House> player2Houses = board.getHousesForPlayer(2);
        player2Houses.get(0).decrement(4);
        player2Houses.get(2).decrement(1);
        player2Houses.get(3).decrement(2);
        player2Houses.get(4).decrement(3);
        player2Houses.get(5).increment(1);
        board.getStoresForPlayer(2).get(0).increment(4);

        List<House> player1Houses = board.getHousesForPlayer(1);
        player1Houses.get(0).increment(1);
        player1Houses.get(1).increment(1);
        player1Houses.get(2).decrement(2);
        player1Houses.get(3).decrement(4);
        player1Houses.get(4).decrement(4);
        player1Houses.get(5).decrement(1);
        board.getStoresForPlayer(1).get(0).increment(14);

        String output = twoPlayerSingleStoreConsoleOutputFormatter.formatOutput(board);
        Assert.assertEquals("+----+-------+-------+-------+-------+-------+-------+----+\n" +
                "| P2 | 6[ 5] | 5[ 1] | 4[ 2] | 3[ 3] | 2[ 4] | 1[ 0] | 14 |\n" +
                "|    |-------+-------+-------+-------+-------+-------|    |\n" +
                "|  4 | 1[ 5] | 2[ 5] | 3[ 2] | 4[ 0] | 5[ 0] | 6[ 3] | P1 |\n" +
                "+----+-------+-------+-------+-------+-------+-------+----+", output);
    }
}
