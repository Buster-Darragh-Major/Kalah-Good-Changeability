package kalah.Bootstrap;

import kalah.Contracts.Factories.BoardFactory;
import kalah.Contracts.Model.Board;
import kalah.Model.HashMapBoard;

public class DefaultBoardFactory implements BoardFactory {
    @Override
    public Board createBoard() {
        return new HashMapBoard.Builder()
                .housesPerPlayer(6)
                .storesPerPlayer(1)
                .numberOfPlayers(2)
                .seedsPerHouse(4)
                .seedsPerStore(0)
                .build();
    }
}
