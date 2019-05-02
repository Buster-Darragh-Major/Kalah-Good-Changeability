package kalah.Contracts.IO;

public interface InputInterpreter {

    boolean isQuit(String str);

    int asNumber(String str);
}
