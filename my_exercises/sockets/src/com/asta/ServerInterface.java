package com.asta;

import java.io.IOException;

public interface ServerInterface {

    void quit() throws IOException;

    double read() throws IOException;

    void offer(double offer, Client client) throws IOException;

    String getItem() throws IOException;

}
