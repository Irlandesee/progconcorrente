package es2.server;

import es2.offer.Offer;
import es2.item.Item;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.LinkedList;

public class Server {

    protected static final int PORT = 8080;
    protected static final String QUIT = "QUIT";
    protected static final String READ = "READ";
    protected static final String OFFER = "OFFER";
    protected static final String GET_ITEM = "GET_ITEM";
    protected static final String GET_OFFERS = "GET_OFFERS";

    protected Item itemInOffer;
    private ConcurrentHashMap<Integer, Offer> offers;
    private LinkedList<ServerSlave> serverSlaves;
    private String auctionWinner;

    public static void main(String[] args){

        ServerSocket ss = null;
        Socket s = null;
        Server server = new Server();

        server.itemInOffer = new Item("item0", 1000);
        server.offers = new ConcurrentHashMap<Integer, Offer>();
        server.serverSlaves = new LinkedList<ServerSlave>();

        try{
            ss = new ServerSocket(PORT);
            while((s = ss.accept()) != null){
                //...
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally {

        }

    }



}
