package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.CLI.EwansExampleConsoleOutputLookAndFeel;
import kalah.CLI.TwoPlayerSingleStoreConsoleOutputFormatter;
import kalah.Contracts.*;
import kalah.Engine.CounterClockwiseSeedSower;
import kalah.Engine.VanillaKalahRules;
import kalah.Engine.VanillaNextPlayerFinder;
import kalah.Engine.VanillaOppositeHouseFinder;
import kalah.Exceptions.EmptyHouseException;
import kalah.Exceptions.HouseDoesntExistException;
import kalah.Model.HashMapBoard;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {

	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}

	public void play(IO io) {
		Board board = new HashMapBoard.Builder()
				.housesPerPlayer(6)
				.storesPerPlayer(1)
				.numberOfPlayers(2)
				.seedsPerHouse(4)
				.seedsPerStore(0)
				.build();
		OutputFormatter outputFormatter = new TwoPlayerSingleStoreConsoleOutputFormatter(
				new EwansExampleConsoleOutputLookAndFeel());
		NextPlayerFinder nextPlayerFinder = new VanillaNextPlayerFinder();
		SeedSower seedSower = new CounterClockwiseSeedSower(nextPlayerFinder);
		OppositeHouseFinder oppositeHouseFinder = new VanillaOppositeHouseFinder();
		KalahRules kalahRules = new VanillaKalahRules(seedSower, nextPlayerFinder, oppositeHouseFinder);

		int playersTurn = 1;
		while (!kalahRules.isGameOver(board, playersTurn)) {
			String[] outputLines = outputFormatter.splitLines(outputFormatter.formatOutput(board));
			for (String line : outputLines) {
				io.println(line);
			}

			String userInput= io.readFromKeyboard(outputFormatter.turnPrompt(playersTurn));
			if (userInput.equals("q")) break;

			try {
				playersTurn = kalahRules.doTurn(board, playersTurn, Integer.parseInt(userInput));
			} catch (EmptyHouseException e) {
				io.println(outputFormatter.emptyHousePrompt(Integer.parseInt(userInput)));
			} catch (NumberFormatException | HouseDoesntExistException e) {
				io.println(outputFormatter.invalidInputPrompt());
			}
		}

		io.println(outputFormatter.gameOverPrompt());
	}
}
