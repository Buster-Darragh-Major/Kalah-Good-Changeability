package kalah.Model;

import kalah.Contracts.Board;

import java.util.*;

public class HashMapBoard implements Board {

    private final HashMap<Integer, Player> _players;

    private HashMapBoard(HashMap<Integer, Player> players) {
        _players = players;
    }

    @Override
    public int getNumberOfPlayers() {
        return _players.size();
    }

    @Override
    public Collection<House> getHousesForPlayer(int playerNo) {
        return _players.get(playerNo).getHouses();
    }

    @Override
    public Collection<Store> getStoresForPlayer(int playerNo) {
        return _players.get(playerNo).getStores();
    }

    public class Builder {

        private int _numberOfPlayers;
        private int _housesPerPlayer;
        private int _seedsPerHouse;
        private int _storesPerPlayer;
        private int _seedsPerStore;

        public Builder(int numberOfPlayers) {
            _numberOfPlayers = numberOfPlayers;
        }

        public Builder housesPerPlayer(int housesPerPlayer) {
            _housesPerPlayer = housesPerPlayer;
            return this;
        }

        public Builder seedsPerHouse(int seedsPerHouse) {
            _seedsPerHouse = seedsPerHouse;
            return this;
        }

        public Builder storesPerPlayer(int storesPerPlayer) {
            _storesPerPlayer = storesPerPlayer;
            return this;
        }

        public Builder seedsPerStore(int seedsPerStore) {
            _seedsPerStore = seedsPerStore;
            return this;
        }

        public Board build() {
            HashMap<Integer, Player> players = new HashMap<>();
            for (int i = 0; i < _numberOfPlayers; i++) {
                List<House> houses = new ArrayList<>();
                for (int j = 0; j < _housesPerPlayer; j++) {
                    houses.add(new House(_seedsPerHouse));
                }
                List<Store> stores = new ArrayList<>();
                for (int j = 0; j< _storesPerPlayer; j++) {
                    stores.add(new Store(_seedsPerStore));
                }

                // Player numbers start from 1
                players.put(i + 1, new Player(houses, stores));
            }

            return new HashMapBoard(players);
        }
    }
}
