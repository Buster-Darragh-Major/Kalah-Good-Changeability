package kalah.Contracts.Model;

import kalah.Model.House;
import kalah.Model.Store;

import java.util.List;

public interface Board {

    int getNumberOfPlayers();

    int getScoreForPlayer(int playerNo);

    int getWinningPlayer();

    List<House> getHousesForPlayer(int playerNo);

    List<Store> getStoresForPlayer(int playerNo);

}
