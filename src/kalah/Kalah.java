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
//		// Replace what's below with your implementation
//		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
//		io.println("| P2 | 6[ 4] | 5[ 4] | 4[ 4] | 3[ 4] | 2[ 4] | 1[ 4] |  0 |");
//		io.println("|    |-------+-------+-------+-------+-------+-------|    |");
//		io.println("|  0 | 1[ 4] | 2[ 4] | 3[ 4] | 4[ 4] | 5[ 4] | 6[ 4] | P1 |");
//		io.println("+----+-------+-------+-------+-------+-------+-------+----+");
//		io.println("Player 1's turn - Specify house number or 'q' to quit: ");
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
			String[] outputLines = outputFormatter.formatOutput(board).split("\n");
			for (String line : outputLines) {
				io.println(line);
			}

			int houseIndex = Integer.parseInt(io.readFromKeyboard(String.format("Player %d's turn - Specify house number or 'q' to quit: ", playersTurn)));
			playersTurn = kalahRules.doTurn(board, playersTurn, houseIndex);
		}


	}
}
