package kalah.IO.Rendering;

import kalah.Contracts.IO.Rendering.ConsoleOutputLookAndFeel;

public class EwansExampleConsoleOutputLookAndFeel implements ConsoleOutputLookAndFeel {

    private static final String CELL_CORNER = "+";
    private static final String CELL_ROOF = "-";
    private static final String CELL_WALL = "|";
    private static final String PLAYER_PREFIX = "P";
    private static final String HOUSE_OPEN_BRACE = "[";
    private static final String HOUSE_CLOSED_BRACE = "]";
    private static final String WHITESPACE = " ";

    @Override
    public String cellCorner() {
        return CELL_CORNER;
    }

    @Override
    public String cellRoof() {
        return CELL_ROOF;
    }

    @Override
    public String cellWall() {
        return CELL_WALL;
    }

    @Override
    public String playerLabel(int playerNo) {
        return new StringBuilder()
                .append(PLAYER_PREFIX)
                .append(playerNo)
                .toString();
    }

    @Override
    public String houseLabel(int houseNo, int noOfSeeds) {
        return new StringBuilder()
                .append(houseNo)
                .append(HOUSE_OPEN_BRACE)
                .append(noOfSeeds > 9 ? "" : " ") // If seed number is > 9 then we don't need a space after open brace
                .append(noOfSeeds)
                .append(HOUSE_CLOSED_BRACE)
                .toString();
    }

    @Override
    public String storeLabel(int noOfSeeds) {
        return new StringBuilder()
                .append(noOfSeeds > 9 ? "" : " ") // If seed number is > 9 then we don't need a space before number
                .append(noOfSeeds)
                .toString();
    }

    @Override
    public String whitespace() {
        return WHITESPACE;
    }
}
