package kalah.Contracts.Rendering;

public interface ConsoleOutputLookAndFeel {

    String cellCorner();

    String cellRoof();

    String cellWall();

    String playerLabel(int playerNo);

    String houseLabel(int houseNo, int noOfSeeds);

    String storeLabel(int noOfSeeds);

    String whitespace();
}
