import kalah.Contracts.Board;
import kalah.Contracts.OppositeHouseFinder;
import kalah.Engine.VanillaOppositeHouseFinder;
import kalah.Model.HashMapBoard;
import kalah.Model.House;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VanillaOppositeHouseFinderTests {

    private Board _board;
    private OppositeHouseFinder _oppositeHouseFinder;

    @Before
    public void runBefore() {
        _board = new HashMapBoard.Builder()
                .housesPerPlayer(6)
                .numberOfPlayers(2)
                .seedsPerHouse(0)
                .seedsPerStore(0)
                .storesPerPlayer(1)
                .build();
        _oppositeHouseFinder = new VanillaOppositeHouseFinder();
    }

    @Test
    public void test0Player1() {
        House oppositeHouse = _oppositeHouseFinder.findOppositeHouse(_board, _board.getHousesForPlayer(1).get(0), 2);

        Assert.assertEquals(5, oppositeHouse.getIndex());
        Assert.assertEquals(2, oppositeHouse.getPlayer());
    }

    @Test
    public void test1Player1() {
        House oppositeHouse = _oppositeHouseFinder.findOppositeHouse(_board, _board.getHousesForPlayer(1).get(1), 2);

        Assert.assertEquals(4, oppositeHouse.getIndex());
        Assert.assertEquals(2, oppositeHouse.getPlayer());
    }

    @Test
    public void test2Player1() {
        House oppositeHouse = _oppositeHouseFinder.findOppositeHouse(_board, _board.getHousesForPlayer(1).get(2), 2);

        Assert.assertEquals(3, oppositeHouse.getIndex());
        Assert.assertEquals(2, oppositeHouse.getPlayer());
    }
    @Test
    public void test3Player1() {
        House oppositeHouse = _oppositeHouseFinder.findOppositeHouse(_board, _board.getHousesForPlayer(1).get(3), 2);

        Assert.assertEquals(2, oppositeHouse.getIndex());
        Assert.assertEquals(2, oppositeHouse.getPlayer());
    }

    @Test
    public void test4Player1() {
        House oppositeHouse = _oppositeHouseFinder.findOppositeHouse(_board, _board.getHousesForPlayer(1).get(4), 2);

        Assert.assertEquals(1, oppositeHouse.getIndex());
        Assert.assertEquals(2, oppositeHouse.getPlayer());
    }

    @Test
    public void test5Player1() {
        House oppositeHouse = _oppositeHouseFinder.findOppositeHouse(_board, _board.getHousesForPlayer(1).get(5), 2);

        Assert.assertEquals(0, oppositeHouse.getIndex());
        Assert.assertEquals(2, oppositeHouse.getPlayer());
    }

    @Test
    public void test0Player2() {
        House oppositeHouse = _oppositeHouseFinder.findOppositeHouse(_board, _board.getHousesForPlayer(2).get(0), 1);

        Assert.assertEquals(5, oppositeHouse.getIndex());
        Assert.assertEquals(1, oppositeHouse.getPlayer());
    }

    @Test
    public void test1Player2() {
        House oppositeHouse = _oppositeHouseFinder.findOppositeHouse(_board, _board.getHousesForPlayer(2).get(1), 1);

        Assert.assertEquals(4, oppositeHouse.getIndex());
        Assert.assertEquals(1, oppositeHouse.getPlayer());
    }

    @Test
    public void test2Player2() {
        House oppositeHouse = _oppositeHouseFinder.findOppositeHouse(_board, _board.getHousesForPlayer(2).get(2), 1);

        Assert.assertEquals(3, oppositeHouse.getIndex());
        Assert.assertEquals(1, oppositeHouse.getPlayer());
    }
    @Test
    public void test3Player2() {
        House oppositeHouse = _oppositeHouseFinder.findOppositeHouse(_board, _board.getHousesForPlayer(2).get(3), 1);

        Assert.assertEquals(2, oppositeHouse.getIndex());
        Assert.assertEquals(1, oppositeHouse.getPlayer());
    }

    @Test
    public void test4Player2() {
        House oppositeHouse = _oppositeHouseFinder.findOppositeHouse(_board, _board.getHousesForPlayer(2).get(4), 1);

        Assert.assertEquals(1, oppositeHouse.getIndex());
        Assert.assertEquals(1, oppositeHouse.getPlayer());
    }

    @Test
    public void test5Player2() {
        House oppositeHouse = _oppositeHouseFinder.findOppositeHouse(_board, _board.getHousesForPlayer(2).get(5), 1);

        Assert.assertEquals(0, oppositeHouse.getIndex());
        Assert.assertEquals(1, oppositeHouse.getPlayer());
    }

}
