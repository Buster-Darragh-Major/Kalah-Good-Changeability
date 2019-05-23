package kalah;

import kalah.Contracts.IO.UserCommunicator;
import kalah.Contracts.Model.Board;
import kalah.Contracts.Rules.KalahRules;
import kalah.IO.UserInput;

public class KalahRunner {

    private KalahRules _kalahRules;
    private UserCommunicator _userCommunicator;

    public KalahRunner(KalahRules kalahRules, UserCommunicator userCommunicator) {
        _kalahRules = kalahRules;
        _userCommunicator = userCommunicator;
    }

    public void run(Board board) {
        int playersTurn = _kalahRules.startingPlayer();
        while (!_kalahRules.isGameOver(board, playersTurn)) {
            _userCommunicator.outputBoard(board);
            try {
                UserInput userInput = _userCommunicator.getUserInput(playersTurn);
                if (userInput.isQuit()) break;
                playersTurn = _kalahRules.doTurn(board, playersTurn, userInput.getNumber());
            } catch (Exception e) {
                _userCommunicator.outputError(e);
            }
        }

        if (_kalahRules.isGameOver(board, playersTurn)) {
            _userCommunicator.outputEndGame(board);
        } else {
            _userCommunicator.outputQuitGame(board);
        }
    }
}
