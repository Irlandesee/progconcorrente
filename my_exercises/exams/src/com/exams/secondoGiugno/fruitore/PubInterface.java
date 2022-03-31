package com.exams.secondoGiugno.fruitore;

import java.io.IOException;
import java.util.LinkedList;

import com.exams.secondoGiugno.notizia.Notizia;

public interface PubInterface {


    public static final int PORT = 9999;
    public static final String OK = "OK";
    public static final String KO = "KO";
    public static final String END = "END";
    public static final String ADD = "ADD";
    public static final String REMOVE = "REMOVE";
    public static final String LISTEN = "LISTEN";
    public static final String QUIT = "QUIT";

    void addTipo(String tipo) throws IOException;
    void removeTipo(String tipo) throws IOException;
    LinkedList<Notizia> listen() throws IOException;
    void quit() throws IOException;
}
