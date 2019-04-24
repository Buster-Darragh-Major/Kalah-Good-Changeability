package kalah.Model;

import java.util.List;

public class Player {

    private List<House> _houses;
    private List<Store> _store;

    public Player(List<House> houses, List<Store> store) {
        _houses = houses;
        _store = store;
    }

    public List<Store> getStores() {
        return _store;
    }

    public List<House> getHouses() {
        return _houses;
    }
}
