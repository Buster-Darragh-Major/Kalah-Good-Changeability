package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.Bootstrap.DefaultBoardFactory;
import kalah.Bootstrap.DefaultKalahRulesFactory;
import kalah.Bootstrap.DefaultOutputFormatterFactory;
import kalah.Contracts.Model.Board;
import kalah.Contracts.Rendering.OutputFormatter;
import kalah.Contracts.Rules.KalahRules;
import kalah.Exceptions.EmptyHouseException;
import kalah.Exceptions.HouseDoesntExistException;

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
		_board = new DefaultBoardFactory().createBoard();
		_outputFormatter = new DefaultOutputFormatterFactory().createOutputFormatter();
		_kalahRules = new DefaultKalahRulesFactory().createKalahRules();
	}
}
