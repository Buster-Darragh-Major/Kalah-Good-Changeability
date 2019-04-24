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
    public List<House> getHousesForPlayer(int playerNo) {
        return _players.get(playerNo).getHouses();
    }

    @Override
    public List<Store> getStoresForPlayer(int playerNo) {
        return _players.get(playerNo).getStores();
    }

     public static class Builder {

        private int _numberOfPlayers;
        private int _housesPerPlayer;
        private int _seedsPerHouse;
        private int _storesPerPlayer;
        private int _seedsPerStore;

        public Builder numberOfPlayers(int numberOfPlayers) {
            _numberOfPlayers = numberOfPlayers;
            return this;
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
            for (int playerNum = 1; playerNum <= _numberOfPlayers; playerNum++) {
                List<House> houses = new ArrayList<>();
                for (int houseIndex = 0; houseIndex < _housesPerPlayer; houseIndex++) {
                    houses.add(new House(playerNum, houseIndex, _seedsPerHouse));
                }
                List<Store> stores = new ArrayList<>();
                for (int storeIndex = 0; storeIndex < _storesPerPlayer; storeIndex++) {
                    stores.add(new Store(playerNum, storeIndex, _seedsPerStore));
                }

                players.put(playerNum, new Player(houses, stores));
            }

            return new HashMapBoard(players);
        }
    }
}