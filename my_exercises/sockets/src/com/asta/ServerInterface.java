package com.asta;

public interface ServerInterface {

    void quit();

    double read();

    void offer(double offer, Client client);

    String getItem();

}
