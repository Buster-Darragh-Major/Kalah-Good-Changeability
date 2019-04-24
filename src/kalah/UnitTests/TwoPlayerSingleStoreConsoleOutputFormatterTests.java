import kalah.CLI.EwansExampleConsoleOutputLookAndFeel;
import kalah.CLI.TwoPlayerSingleStoreConsoleOutputFormatter;
import kalah.Contracts.Board;
import kalah.Contracts.OutputFormatter;
import kalah.Model.HashMapBoard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void TestLine1() {
        String output = twoPlayerSingleStoreConsoleOutputFormatter.formatOutput(_board);
        String[] lines = output.split("\n");

        Assert.assertEquals("+----+-------+-------+-------+-------+-------+-------+----+", lines[0]);
    }

    @Test
    public void TestLine2() {
        String output = twoPlayerSingleStoreConsoleOutputFormatter.formatOutput(_board);
        String[] lines = output.split("\n");

        Assert.assertEquals("| P2 | 6[ 4] | 5[ 4] | 4[ 4] | 3[ 4] | 2[ 4] | 1[ 4] |  0 |", lines[1]);
    }

    @Test
    public void TestLine3() {
        String output = twoPlayerSingleStoreConsoleOutputFormatter.formatOutput(_board);
        String[] lines = output.split("\n");

        Assert.assertEquals("|    |-------+-------+-------+-------+-------+-------|    |", lines[2]);
    }

    @Test
    public void TestLine5() {
        String output = twoPlayerSingleStoreConsoleOutputFormatter.formatOutput(_board);
        String[] lines = output.split("\n");

        Assert.assertEquals("+----+-------+-------+-------+-------+-------+-------+----+", lines[4]);
    }
}
