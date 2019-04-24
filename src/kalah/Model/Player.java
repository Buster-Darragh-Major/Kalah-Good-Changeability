package kalah.Model;

import java.util.Collection;

public class Player {

    private Collection<House> _houses;
    private Collection<Store> _store;

    public Player(Collection<House> houses, Collection<Store> store) {
        _houses = houses;
        _store = store;
    }

    public Collection<Store> getStores() {
        return _store;
    }

    public Collection<House> getHouses() {
        return _houses;
    }
}
