package kalah.Contracts.IO.Rendering;

import kalah.Contracts.Model.Board;

public interface OutputFormatter {

    String formatOutput(Board board);

    String[] splitLines(String string);

    String turnPrompt(int playerTurn);

    String emptyHousePrompt();

    String invalidInputPrompt();

    String gameOverPrompt();

    String playerScore(int player, int score);

    String playerWins(int player);

    String tie();
}
