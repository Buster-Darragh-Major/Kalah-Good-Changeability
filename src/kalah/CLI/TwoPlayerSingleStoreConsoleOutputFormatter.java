package kalah.CLI;

import kalah.Contracts.Board;
import kalah.Contracts.ConsoleOutputLookAndFeel;
import kalah.Contracts.OutputFormatter;
import kalah.Model.House;
import kalah.Model.Store;

import java.util.Collection;

public class TwoPlayerSingleStoreConsoleOutputFormatter implements OutputFormatter {

    private static final int PLAYER_1_INDEX = 1;
    private static final int PLAYER_2_INDEX = 2;

    private static final String NEW_LINE = "\n";

    private static final int STORE_WIDTH = 4;
    private static final int HOUSE_WIDTH = 7;

    private ConsoleOutputLookAndFeel _lookAndFeel;

    public TwoPlayerSingleStoreConsoleOutputFormatter(ConsoleOutputLookAndFeel lookAndFeel) {
        _lookAndFeel = lookAndFeel;
    }

    @Override
    public String formatOutput(Board board) {
        // Unpack
        Collection<House> player1Houses = board.getHousesForPlayer(PLAYER_1_INDEX);
        Collection<House> player2Houses = board.getHousesForPlayer(PLAYER_2_INDEX);
        Store player1Store = board.getStoresForPlayer(PLAYER_1_INDEX).iterator().next();
        Store player2Store = board.getStoresForPlayer(PLAYER_2_INDEX).iterator().next();

        return new StringBuilder()
                .append(synthesizeLine1And5(player2Houses.size()))
                .append(NEW_LINE)
                // TODO: line 2
                .append(NEW_LINE)
                // TODO: line 3
                .append(NEW_LINE)
                // TODO: line 4
                .append(NEW_LINE)
                .append(synthesizeLine1And5(player1Houses.size()))
                .toString();
    }

    private String synthesizeLine1And5(int numberOfHouses) {
        StringBuilder sb = new StringBuilder();
        sb.append(_lookAndFeel.cellCorner())
                .append(repeat(_lookAndFeel.cellRoof(), STORE_WIDTH));

        for (int i = 0; i < numberOfHouses; i++) {
            sb.append(_lookAndFeel.cellCorner())
                    .append(repeat(_lookAndFeel.cellRoof(), HOUSE_WIDTH));
        }

        sb.append(_lookAndFeel.cellCorner())
                .append(repeat(_lookAndFeel.cellRoof(), STORE_WIDTH))
                .append(_lookAndFeel.cellCorner());

        return sb.toString();
    }

    private String repeat(String toRepeat, int noOfTimes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < noOfTimes; i++) {
            sb.append(toRepeat);
        }
        return sb.toString();
    }
}
