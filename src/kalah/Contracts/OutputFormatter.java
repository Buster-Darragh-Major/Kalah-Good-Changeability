package kalah.Contracts;

public interface OutputFormatter {

    String formatOutput(Board board);

    String[] splitLines(String string);

    String turnPrompt(int playerTurn);

    String emptyHousePrompt(int houseNo);

    String invalidInputPrompt();

    String gameOverPrompt();
}
