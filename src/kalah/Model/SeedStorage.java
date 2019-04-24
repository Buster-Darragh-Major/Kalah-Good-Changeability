package kalah.Model;

public abstract class SeedStorage {

    private int _player;
    private int _index;

    protected int _noSeeds;

    protected SeedStorage(int player, int index, int noSeeds) {
        _player = player;
        _index = index;
        _noSeeds = noSeeds;
    }

    public int getSeeds() {
        return _noSeeds;
    }

    public int getPlayer() {
        return _player;
    }

    public int getIndex() {
        return _index;
    }
}
