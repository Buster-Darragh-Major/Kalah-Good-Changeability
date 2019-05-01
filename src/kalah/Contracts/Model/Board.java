package kalah.Contracts.Model;

import kalah.Model.House;
import kalah.Model.Store;

import java.util.List;

public interface Board {

    int getNumberOfPlayers();

    List<House> getHousesForPlayer(int playerNo);

    List<Store> getStoresForPlayer(int playerNo);

}
