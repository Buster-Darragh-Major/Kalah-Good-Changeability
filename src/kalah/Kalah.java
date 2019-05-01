package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.CLI.EwansExampleConsoleOutputLookAndFeel;
import kalah.CLI.TwoPlayerSingleStoreConsoleOutputFormatter;
import kalah.Contracts.Model.Board;
import kalah.Contracts.Rendering.OutputFormatter;
import kalah.Contracts.Rules.KalahRules;
import kalah.Contracts.Rules.NextPlayerFinder;
import kalah.Contracts.Rules.OppositeHouseFinder;
import kalah.Contracts.Rules.SeedSower;
import kalah.Rules.CounterClockwiseSeedSower;
import kalah.Rules.VanillaKalahRules;
import kalah.Rules.VanillaNextPlayerFinder;
import kalah.Rules.VanillaOppositeHouseFinder;
import kalah.Exceptions.EmptyHouseException;
import kalah.Exceptions.HouseDoesntExistException;
import kalah.Model.HashMapBoard;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {

	private Board _board;
	private OutputFormatter _outputFormatter;
	private KalahRules _kalahRules;

	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}

	public void play(IO io) {
		bootstrap();

		int playersTurn = 1;
		while (!_kalahRules.isGameOver(_board, playersTurn)) {
			String[] outputLines = _outputFormatter.splitLines(_outputFormatter.formatOutput(_board));
			for (String line : outputLines) {
				io.println(line);
			}

			String userInput= io.readFromKeyboard(_outputFormatter.turnPrompt(playersTurn));
			if (userInput.equals("q")) break;

			try {
				playersTurn = _kalahRules.doTurn(_board, playersTurn, Integer.parseInt(userInput));
			} catch (EmptyHouseException e) {
				io.println(_outputFormatter.emptyHousePrompt(Integer.parseInt(userInput)));
			} catch (NumberFormatException | HouseDoesntExistException e) {
				io.println(_outputFormatter.invalidInputPrompt());
			}
		}

		io.println(_outputFormatter.gameOverPrompt());
	}

	private void bootstrap() {
		_board = new HashMapBoard.Builder()
				.housesPerPlayer(6)
				.storesPerPlayer(1)
				.numberOfPlayers(2)
				.seedsPerHouse(4)
				.seedsPerStore(0)
				.build();
		_outputFormatter = new TwoPlayerSingleStoreConsoleOutputFormatter(
				new EwansExampleConsoleOutputLookAndFeel());
		NextPlayerFinder nextPlayerFinder = new VanillaNextPlayerFinder();
		SeedSower seedSower = new CounterClockwiseSeedSower(nextPlayerFinder);
		OppositeHouseFinder oppositeHouseFinder = new VanillaOppositeHouseFinder();
		_kalahRules = new VanillaKalahRules(seedSower, nextPlayerFinder, oppositeHouseFinder);
	}
}
