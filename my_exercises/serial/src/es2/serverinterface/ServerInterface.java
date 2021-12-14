package es2.serverinterface;

import es2.offer.Offer;
import java.io.IOException;
import java.util.List;

public interface ServerInterface {

    static final int PORT = 8080;
    static final String OK = "OK";
    static final String KO = "KO";
    static final String QUIT = "QUIT";
    static final String READ = "READ";
    static final String OFFER = "OFFER";
    static final String GET_ITEM = "GET_ITEM";
    static final String GET_OFFERS  = "GET_OFFERS";

    void quit() throws IOException;
    double read() throws IOException, ClassNotFoundException;
    void offer(Offer offer) throws IOException, ClassNotFoundException;
    List<Offer> getOffers()throws IOException, ClassNotFoundException;
    String getItem() throws IOException, ClassNotFoundException;
}
