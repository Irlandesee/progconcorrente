package es2.serverinterface;

import es2.offer.Offer;
import java.io.IOException;

public interface ServerInterface {

    static final String OK = "OK";
    static final String KO = "KO";

    void quit() throws IOException, ClassNotFoundException;
    double read() throws IOException, ClassNotFoundException;
    void offer(Offer offer) throws IOException, ClassNotFoundException;
    void getOffers()throws IOException;
    String getItem() throws IOException;
}
