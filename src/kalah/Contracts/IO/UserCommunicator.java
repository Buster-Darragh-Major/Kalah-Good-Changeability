package kalah.Contracts.IO;

import kalah.Contracts.Model.Board;
import kalah.IO.UserInput;

public interface UserCommunicator {

    void outputBoard(Board board);

    UserInput getUserInput(int playersTurn);

    void outputError(Exception e);

    void outputEndGame(Board board);

    void outputQuitGame(Board board);
}
