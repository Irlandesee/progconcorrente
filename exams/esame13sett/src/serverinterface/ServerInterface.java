package serverinterface;

public interface ServerInterface {

    enum Winner {NONE, PLAYER1, PLAYER2, EVEN}

    int serverPort = 8080;

    void reset();

    boolean set(int r, int c, char s);

    void show();

    Winner cellOwner(char x);

    Winner whoWins();

}
