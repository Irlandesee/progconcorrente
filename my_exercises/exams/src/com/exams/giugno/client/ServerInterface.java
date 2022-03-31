package com.exams.giugno.client;

import java.io.IOException;
import com.exams.giugno.resource.Resource;

public interface ServerInterface {

    public static final int PORT = 9999;
    public static final String OK = "OK";
    public static final String KO = "KO";
    public static final String WAIT = "WAIT";
    public static final String QUIT = "QUIT";
    public static final String ADD = "ADD";
    public static final String REMOVE = "REMOVE";
    public static final String UNDEFINED = "UNDEFIEND";

    public boolean add(Resource r) throws IOException;
    public Resource remove() throws IOException;
    public void quit() throws IOException;

}
