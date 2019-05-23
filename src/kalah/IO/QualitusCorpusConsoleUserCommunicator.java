package kalah.IO;

import com.qualitascorpus.testsupport.IO;
import kalah.Contracts.IO.InputInterpreter;
import kalah.Contracts.IO.Rendering.OutputFormatter;
import kalah.Contracts.IO.UserCommunicator;
import kalah.Contracts.Model.Board;
import kalah.Exceptions.EmptyHouseException;
import kalah.Exceptions.HouseDoesntExistException;

public class QualitusCorpusConsoleUserCommunicator implements UserCommunicator {

    private IO _io;
    private final OutputFormatter _outputFormatter;
    private final InputInterpreter _inputInterpreter;

    public QualitusCorpusConsoleUserCommunicator(IO io, OutputFormatter outputFormatter, InputInterpreter inputInterpreter) {
        _io = io;
        _outputFormatter = outputFormatter;
        _inputInterpreter = inputInterpreter;
    }

    @Override
    public void outputBoard(Board board) {
        printBoard(board);
    }

    @Override
    public UserInput getUserInput(int playersTurn) {
        String userInput= _io.readFromKeyboard(_outputFormatter.turnPrompt(playersTurn));
        return new UserInput(userInput, _inputInterpreter);
    }

    @Override
    public void outputError(Exception e) {
        if (e instanceof EmptyHouseException) {
            _io.println(_outputFormatter.emptyHousePrompt());
        } else if (e instanceof NumberFormatException || e instanceof HouseDoesntExistException) {
            _io.println(_outputFormatter.invalidInputPrompt());
        } else {
            _io.println(e.toString());
        }
    }

    @Override
    public void outputEndGame(Board board) {
        printBoard(board);
        _io.println(_outputFormatter.gameOverPrompt());
        printBoard(board);

        for (int playerNo = 1; playerNo <= board.getNumberOfPlayers(); playerNo++) {
            _io.println(_outputFormatter.playerScore(playerNo, board.getScoreForPlayer(playerNo)));
        }
        if (board.getWinningPlayer() == -1) {
            _io.println(_outputFormatter.tie());
        } else {
            _io.println(_outputFormatter.playerWins(board.getWinningPlayer()));
        }
    }

    @Override
    public void outputQuitGame(Board board) {
        _io.println(_outputFormatter.gameOverPrompt());
        printBoard(board);
    }

    private void printBoard(Board board) {
        String[] outputLines = _outputFormatter.splitLines(_outputFormatter.formatOutput(board));
        for (String line : outputLines) {
            _io.println(line);
        }
    }
}
