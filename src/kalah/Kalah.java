package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.Bootstrap.DefaultBoardFactory;
import kalah.Bootstrap.DefaultInputInterpreterFactory;
import kalah.Bootstrap.DefaultKalahRulesFactory;
import kalah.Bootstrap.DefaultOutputFormatterFactory;
import kalah.Contracts.IO.InputInterpreter;
import kalah.Contracts.IO.Rendering.OutputFormatter;
import kalah.Contracts.Model.Board;
import kalah.Contracts.Rules.KalahRules;
import kalah.Exceptions.EmptyHouseException;
import kalah.Exceptions.HouseDoesntExistException;
import kalah.test.TestKalah;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {

	private Board _board;
	private OutputFormatter _outputFormatter;
	private KalahRules _kalahRules;
	private InputInterpreter _inputInterpreter;

	public static void main(String[] args) {
//		TestKalah tk = new TestKalah();
//		tk.run();
//		tk.testExactLapCapture();
//		tk.testCapture();
//		tk.testQuit();
//		tk.testSimpleStart();
//		tk.testP1Continue();
//		tk.testSimpleTwoMoves();
//		tk.testSingleWrap();
//		tk.testContinueWrap();
//		tk.testCapture();
//		tk.testEmptyHouseCapture();
//		tk.testFullGame1();
//		tk.testFullGame2();
//		tk.testFullGameStore();
//		tk.testFullGameEmpty();
//		tk.testFullGameTie();
//		tk.testFullGameEmptyHouses();
//		tk.testUseEmpty();
//		tk.testWrapped();
//		tk.testDoubleWrap();
//		tk.testExactLapCapture();
//		tk.testExactLapEmptyCapture();
		new Kalah().play(new MockIO());
	}

	public void play(IO io) {
		bootstrap();

		int playersTurn = _kalahRules.startingPlayer();
		while (!_kalahRules.isGameOver(_board, playersTurn)) {
			printBoard(io);

			String userInput= io.readFromKeyboard(_outputFormatter.turnPrompt(playersTurn));
			if (_inputInterpreter.isQuit(userInput)) break;

			try {
				playersTurn = _kalahRules.doTurn(_board, playersTurn, _inputInterpreter.asNumber(userInput));
			} catch (EmptyHouseException e) {
				io.println(_outputFormatter.emptyHousePrompt(Integer.parseInt(userInput)));
			} catch (NumberFormatException | HouseDoesntExistException e) {
				io.println(_outputFormatter.invalidInputPrompt());
			}
		}

		if (_kalahRules.isGameOver(_board, playersTurn)) {
			printBoard(io);
		}

		io.println(_outputFormatter.gameOverPrompt());
	}

	private void printBoard(IO io) {
		String[] outputLines = _outputFormatter.splitLines(_outputFormatter.formatOutput(_board));
		for (String line : outputLines) {
			io.println(line);
		}
	}

	private void bootstrap() {
		_board = new DefaultBoardFactory().createBoard();
		_outputFormatter = new DefaultOutputFormatterFactory().createOutputFormatter();
		_kalahRules = new DefaultKalahRulesFactory().createKalahRules();
		_inputInterpreter = new DefaultInputInterpreterFactory().createInputInterpreter();
	}
}
