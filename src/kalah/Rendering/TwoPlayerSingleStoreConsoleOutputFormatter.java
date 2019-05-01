package kalah.Rendering;

import kalah.Contracts.Model.Board;
import kalah.Contracts.Rendering.ConsoleOutputLookAndFeel;
import kalah.Contracts.Rendering.OutputFormatter;
import kalah.Model.House;
import kalah.Model.Store;

import java.util.List;

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
        List<House> player1Houses = board.getHousesForPlayer(PLAYER_1_INDEX);
        List<House> player2Houses = board.getHousesForPlayer(PLAYER_2_INDEX);
        Store player1Store = board.getStoresForPlayer(PLAYER_1_INDEX).iterator().next();
        Store player2Store = board.getStoresForPlayer(PLAYER_2_INDEX).iterator().next();

        return new StringBuilder()
                .append(synthesizeLine1And5(player1Houses.size()))
                .append(NEW_LINE)
                .append(synthesizeLine2(PLAYER_2_INDEX, player2Houses, player1Store))
                .append(NEW_LINE)
                .append(synthesizeLine3(player1Houses.size()))
                .append(NEW_LINE)
                .append(synthesizeLine4(PLAYER_1_INDEX, player1Houses, player2Store))
                .append(NEW_LINE)
                .append(synthesizeLine1And5(player1Houses.size()))
                .toString();
    }

    @Override
    public String[] splitLines(String string) {
        return string.split("\n");
    }

    @Override
    public String turnPrompt(int playerTurn) {
        return String.format("Player %d's turn - Specify house number or 'q' to quit: ", playerTurn);
    }

    @Override
    public String emptyHousePrompt(int houseNo) {
        return String.format("You cannot sow seeds from house %d as it is empty", houseNo);
    }

    @Override
    public String invalidInputPrompt() {
        return "Invalid input";
    }

    @Override
    public String gameOverPrompt() {
        return "Game over";
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

    private String synthesizeLine2(int playerNumber, List<House> houses, Store playerStore) {
        StringBuilder sb = new StringBuilder();
        sb.append(_lookAndFeel.cellWall())
                .append(_lookAndFeel.whitespace())
                .append(_lookAndFeel.playerLabel(playerNumber))
                .append(_lookAndFeel.whitespace());

        int houseIndex = houses.size();
        for (int i = houses.size() - 1; i >= 0; i--) {
            sb.append(_lookAndFeel.cellWall())
                    .append(_lookAndFeel.whitespace())
                    .append(_lookAndFeel.houseLabel(houseIndex--, houses.get(i).getSeeds()))
                    .append(_lookAndFeel.whitespace());
        }

        sb.append(_lookAndFeel.cellWall())
                .append(_lookAndFeel.whitespace())
                .append(_lookAndFeel.storeLabel(playerStore.getSeeds()))
                .append(_lookAndFeel.whitespace())
                .append(_lookAndFeel.cellWall());

        return sb.toString();
    }

    private String synthesizeLine3(int numberOfHouses) {
        StringBuilder sb = new StringBuilder();
        sb.append(_lookAndFeel.cellWall())
                .append(repeat(_lookAndFeel.whitespace(), STORE_WIDTH));

        for (int i = 0; i < numberOfHouses; i++) {
            if (i == 0) { // If its the first house add a wall on the left instead
                sb.append(_lookAndFeel.cellWall());
            } else {
                sb.append(_lookAndFeel.cellCorner());
            }
            sb.append(repeat(_lookAndFeel.cellRoof(), HOUSE_WIDTH));
        }

        sb.append(_lookAndFeel.cellWall())
                .append(repeat(_lookAndFeel.whitespace(), STORE_WIDTH))
                .append(_lookAndFeel.cellWall());

        return sb.toString();
    }

    private String synthesizeLine4(int playerNumber, List<House> houses, Store playerStore) {
        StringBuilder sb = new StringBuilder();
        sb.append(_lookAndFeel.cellWall())
                .append(_lookAndFeel.whitespace())
                .append(_lookAndFeel.storeLabel(playerStore.getSeeds()))
                .append(_lookAndFeel.whitespace());

        int houseIndex = 1;
        for (House house : houses) {
            sb.append(_lookAndFeel.cellWall())
                    .append(_lookAndFeel.whitespace())
                    .append(_lookAndFeel.houseLabel(houseIndex++, house.getSeeds()))
                    .append(_lookAndFeel.whitespace());
        }

        sb.append(_lookAndFeel.cellWall())
                .append(_lookAndFeel.whitespace())
                .append(_lookAndFeel.playerLabel(playerNumber))
                .append(_lookAndFeel.whitespace())
                .append(_lookAndFeel.cellWall());

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
