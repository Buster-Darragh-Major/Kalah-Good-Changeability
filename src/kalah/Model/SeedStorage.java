package kalah.Model;

public abstract class SeedStorage {

    protected int _noSeeds;

    protected SeedStorage(int noSeeds) {
        _noSeeds = noSeeds;
    }

    public int getSeeds() {
        return _noSeeds;
    }
}
