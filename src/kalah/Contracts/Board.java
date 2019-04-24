package kalah.Contracts;

import kalah.Model.House;
import kalah.Model.Store;

import java.util.Collection;

public interface Board {

    int getNumberOfPlayers();

    Collection<House> getHousesForPlayer(int playerNo);

    Collection<Store> getStoresForPlayer(int playerNo);

}
