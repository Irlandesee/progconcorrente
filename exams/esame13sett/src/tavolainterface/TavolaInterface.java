package tavolainterface;

public interface TavolaInterface {

    enum Winner {NONE, PLAYER1, PLAYER2, EVEN}

    int serverPort = 8070;

    void reset();

    boolean set(int r, int c, char s);

    Winner whoWins();

}
