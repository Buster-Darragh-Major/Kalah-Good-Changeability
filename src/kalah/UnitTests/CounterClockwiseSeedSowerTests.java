import kalah.Contracts.Board;
import kalah.Contracts.SeedSower;
import kalah.Engine.CounterClockwiseSeedSower;
import kalah.Engine.VanillaNextPlayerFinder;
import kalah.Model.HashMapBoard;
import kalah.Model.House;
import kalah.Model.Store;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CounterClockwiseSeedSowerTests {

    private Board _board;
    private SeedSower _seedSower;

    @Before
    public void runBefore() {
        _board = new HashMapBoard.Builder()
                .housesPerPlayer(6)
                .numberOfPlayers(2)
                .seedsPerHouse(0)
                .seedsPerStore(0)
                .storesPerPlayer(1)
                .build();
        _seedSower = new CounterClockwiseSeedSower(new VanillaNextPlayerFinder());
    }

    @Test
    public void testSingleSeedSow() {
        House terminalHouse = (House) _seedSower.sowSeeds(1, _board, 1, 1);

        Assert.assertEquals(1, terminalHouse.getIndex());
        Assert.assertEquals(1, terminalHouse.getPlayer());
        Assert.assertEquals(1, _board.getHousesForPlayer(1).get(1).getSeeds());
    }

    @Test
    public void testSinglePlayerSow() {
        House terminalHouse = (House) _seedSower.sowSeeds(5, _board, 1, 1);

        Assert.assertEquals(5, terminalHouse.getIndex());
        Assert.assertEquals(1, terminalHouse.getPlayer());
        Assert.assertEquals(1, _board.getHousesForPlayer(1).get(1).getSeeds());
        Assert.assertEquals(1, _board.getHousesForPlayer(1).get(2).getSeeds());
        Assert.assertEquals(1, _board.getHousesForPlayer(1).get(3).getSeeds());
        Assert.assertEquals(1, _board.getHousesForPlayer(1).get(4).getSeeds());
        Assert.assertEquals(1, _board.getHousesForPlayer(1).get(5).getSeeds());
    }

    @Test
    public void testTwoPlayerSow() {
        House terminalHouse = (House) _seedSower.sowSeeds(10, _board, 1, 1);

        Assert.assertEquals(3, terminalHouse.getIndex());
        Assert.assertEquals(2, terminalHouse.getPlayer());
        Assert.assertEquals(1, _board.getHousesForPlayer(1).get(1).getSeeds());
        Assert.assertEquals(1, _board.getHousesForPlayer(1).get(2).getSeeds());
        Assert.assertEquals(1, _board.getHousesForPlayer(1).get(3).getSeeds());
        Assert.assertEquals(1, _board.getHousesForPlayer(1).get(4).getSeeds());
        Assert.assertEquals(1, _board.getHousesForPlayer(1).get(5).getSeeds());
        Assert.assertEquals(1, _board.getStoresForPlayer(1).get(0).getSeeds());
        Assert.assertEquals(1, _board.getHousesForPlayer(2).get(0).getSeeds());
        Assert.assertEquals(1, _board.getHousesForPlayer(2).get(1).getSeeds());
        Assert.assertEquals(1, _board.getHousesForPlayer(2).get(2).getSeeds());
        Assert.assertEquals(1, _board.getHousesForPlayer(2).get(3).getSeeds());
    }

    @Test
    public void testThreePlayerSow() {
        Store terminalStore = (Store) _seedSower.sowSeeds(20, _board, 1, 1);

        Assert.assertEquals(0, terminalStore.getIndex());
        Assert.assertEquals(1, terminalStore.getPlayer());
        Assert.assertEquals(1, _board.getHousesForPlayer(1).get(0).getSeeds());
        Assert.assertEquals(2, _board.getHousesForPlayer(1).get(1).getSeeds());
        Assert.assertEquals(2, _board.getHousesForPlayer(1).get(2).getSeeds());
        Assert.assertEquals(2, _board.getHousesForPlayer(1).get(3).getSeeds());
        Assert.assertEquals(2, _board.getHousesForPlayer(1).get(4).getSeeds());
        Assert.assertEquals(2, _board.getHousesForPlayer(1).get(5).getSeeds());
        Assert.assertEquals(2, _board.getStoresForPlayer(1).get(0).getSeeds());
        Assert.assertEquals(1, _board.getHousesForPlayer(2).get(0).getSeeds());
        Assert.assertEquals(1, _board.getHousesForPlayer(2).get(1).getSeeds());
        Assert.assertEquals(1, _board.getHousesForPlayer(2).get(2).getSeeds());
        Assert.assertEquals(1, _board.getHousesForPlayer(2).get(3).getSeeds());
        Assert.assertEquals(1, _board.getHousesForPlayer(2).get(4).getSeeds());
        Assert.assertEquals(1, _board.getHousesForPlayer(2).get(5).getSeeds());
        Assert.assertEquals(1, _board.getStoresForPlayer(2).get(0).getSeeds());
    }
}
