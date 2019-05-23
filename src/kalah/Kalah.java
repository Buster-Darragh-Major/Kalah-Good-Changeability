package kalah;

import com.qualitascorpus.testsupport.IO;
import com.qualitascorpus.testsupport.MockIO;
import kalah.Bootstrap.DefaultBoardFactory;
import kalah.Bootstrap.DefaultKalahRunnerFactory;
import kalah.Contracts.Model.Board;

/**
 * This class is the starting point for a Kalah implementation using
 * the test infrastructure.
 */
public class Kalah {

	private Board _board;
	private KalahRunner _kalahRunner;

	public static void main(String[] args) {
		new Kalah().play(new MockIO());
	}

	public void play(IO io) {
		_board = new DefaultBoardFactory().createBoard();
		_kalahRunner = new DefaultKalahRunnerFactory(io).createKalahRunner();

		_kalahRunner.run(_board);
	}
}
