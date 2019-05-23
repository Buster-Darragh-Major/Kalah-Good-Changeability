package kalah.IO.Rendering;

import kalah.Contracts.IO.Rendering.ConsoleOutputLookAndFeel;
import kalah.Contracts.IO.Rendering.OutputFormatter;
import kalah.Contracts.Model.Board;
import kalah.Exceptions.InvalidBoardException;
import kalah.Model.House;
import kalah.Model.Store;

import java.util.List;

public class Assignment5VerticalLayout implements OutputFormatter {

    private static final int PLAYER_1_INDEX = 1;
    private static final int PLAYER_2_INDEX = 2;
    private static final int COLUMN_WIDTH = 7;
    private static final String NEW_LINE = "\n";
    private static final String QUIT_SYMBOL = "q";

    private ConsoleOutputLookAndFeel _lookAndFeel;

    public Assignment5VerticalLayout(ConsoleOutputLookAndFeel lookAndFeel) {
        _lookAndFeel = lookAndFeel;
    }

    @Override
    public String formatOutput(Board board) {
        if (board.getNumberOfPlayers() != 2) throw new InvalidBoardException(String.format(
                "This implementation of OutputFormatter expects a board with 2 players. Received: %d players.",
                board.getNumberOfPlayers()));

        List<House> player1Houses = board.getHousesForPlayer(PLAYER_1_INDEX);
        List<House> player2Houses = board.getHousesForPlayer(PLAYER_2_INDEX);
        Store player1Store = board.getStoresForPlayer(PLAYER_1_INDEX).iterator().next();
        Store player2Store = board.getStoresForPlayer(PLAYER_2_INDEX).iterator().next();

        StringBuilder sb = new StringBuilder();
        sb.append(synthesizeLine1And12())
                .append(NEW_LINE)
                .append(synthesizeLine2(player2Store))
                .append(NEW_LINE)
                .append(synthesizeLine3And10())
                .append(NEW_LINE);
        for (int i = 0; i < player1Houses.size(); i++) {
            sb.append(synthesizeHouseRow(player1Houses.get(i), player2Houses.get(player1Houses.size() - 1 - i)))
                    .append(NEW_LINE);
        }
        sb.append(synthesizeLine3And10())
                .append(NEW_LINE)
                .append(synthesizeLine11(player1Store))
                .append(NEW_LINE)
                .append(synthesizeLine1And12());

        return sb.toString();
    }

    @Override
    public String[] splitLines(String string) {
        return string.split("\n");
    }

    @Override
    public String turnPrompt(int playerTurn) {
        return String.format("Player P%d's turn - Specify house number or '%s' to quit:", playerTurn, QUIT_SYMBOL);
    }

    @Override
    public String emptyHousePrompt(int houseNo) {
        return "House is empty. Move again.";
    }

    @Override
    public String invalidInputPrompt() {
        return "Invalid input";
    }

    @Override
    public String gameOverPrompt() {
        return "Game over";
    }

    @Override
    public String playerScore(int player, int score) {
        return String.format("\tplayer %d:%d", player, score);
    }

    @Override
    public String playerWins(int player) {
        return String.format("Player %d wins!", player);
    }

    @Override
    public String tie() {
        return "A tie!";
    }

    private String synthesizeLine3And10() {
        StringBuilder sb = new StringBuilder();
        sb.append(_lookAndFeel.cellCorner());
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < COLUMN_WIDTH; j++) {
                sb.append(_lookAndFeel.cellRoof());
            }
            sb.append(_lookAndFeel.cellCorner());
        }

        return sb.toString();
    }

    private String synthesizeLine2(Store playerStore) {
        StringBuilder sb = new StringBuilder();
        sb.append(_lookAndFeel.cellWall());
        for (int i = 0; i < COLUMN_WIDTH; i++) {
            sb.append(_lookAndFeel.whitespace());
        }
        sb.append(_lookAndFeel.cellWall())
                .append(_lookAndFeel.whitespace())
                .append(_lookAndFeel.playerLabel(PLAYER_2_INDEX))
                .append(_lookAndFeel.whitespace())
                .append(_lookAndFeel.storeLabel(playerStore.getSeeds()))
                .append(_lookAndFeel.whitespace())
                .append(_lookAndFeel.cellWall());

        return sb.toString();
    }

    private String synthesizeLine1And12() {
        StringBuilder sb = new StringBuilder();
        sb.append(_lookAndFeel.cellCorner());
        for (int i = 0; i < COLUMN_WIDTH * 2 + 1; i++) {
            sb.append(_lookAndFeel.cellRoof());
        }
        sb.append(_lookAndFeel.cellCorner());

        return sb.toString();
    }

    private String synthesizeHouseRow(House leftHouse, House rightHouse) {
        StringBuilder sb = new StringBuilder();
        sb.append(_lookAndFeel.cellWall())
                .append(_lookAndFeel.whitespace())
                .append(_lookAndFeel.houseLabel(leftHouse.getIndex() + 1, leftHouse.getSeeds()))
                .append(_lookAndFeel.whitespace())
                .append(_lookAndFeel.cellWall())
                .append(_lookAndFeel.whitespace())
                .append(_lookAndFeel.houseLabel(rightHouse.getIndex() + 1, rightHouse.getSeeds()))
                .append(_lookAndFeel.whitespace())
                .append(_lookAndFeel.cellWall());

        return sb.toString();
    }

    private String synthesizeLine11(Store playerStore) {
        StringBuilder sb = new StringBuilder();
        sb.append(_lookAndFeel.cellWall())
                .append(_lookAndFeel.whitespace())
                .append(_lookAndFeel.playerLabel(PLAYER_1_INDEX))
                .append(_lookAndFeel.whitespace())
                .append(_lookAndFeel.storeLabel(playerStore.getSeeds()))
                .append(_lookAndFeel.whitespace())
                .append(_lookAndFeel.cellWall());
        for (int i = 0; i < COLUMN_WIDTH; i++) {
            sb.append(_lookAndFeel.whitespace());
        }
        sb.append(_lookAndFeel.cellWall());

        return sb.toString();
    }
}
