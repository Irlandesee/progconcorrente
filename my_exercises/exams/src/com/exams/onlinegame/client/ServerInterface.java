package com.exams.onlinegame.client;

import java.io.IOException;
import com.exams.onlinegame.move.Move;

public interface ServerInterface {

    public static final int PORT = 9999;
    public static final String nextMove = "NEXT";
    public static final String quit = "QUIT";
    public static final String winner = "WINNER";
    public static final String loser = "LOSER";

    public void next(Move mv) throws IOException;
    public void quit() throws IOException;

}
