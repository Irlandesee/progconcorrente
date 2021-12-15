package es2.server;

import es2.offer.Offer;
import es2.item.Item;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Server {

    private static final short seconds = 60;

    protected static final int PORT = 8080;
    protected static final String QUIT = "QUIT";
    protected static final String READ = "READ";
    protected static final String OFFER = "OFFER";
    protected static final String GET_ITEM = "GET_ITEM";
    protected static final String GET_OFFERS = "GET_OFFERS";
    protected static final String OK = "OK";
    protected static final String KO = "KO";

    protected Item itemInOffer;
    protected Offer topOffer;

    private LinkedBlockingQueue<Offer> offers;
    private LinkedList<ServerSlave> serverSlaves;
    private int auctionWinner; //the client that has won the auction

    public static void main(String[] args){

        ServerSocket ss = null;
        Socket s = null;
        Server server = new Server();

        server.itemInOffer = new Item("item0", 1000);
        server.offers = new LinkedBlockingQueue<Offer>();
        server.serverSlaves = new LinkedList<ServerSlave>();

        try{
            ss = new ServerSocket(PORT);
            int i = 0;
            while((s = ss.accept()) != null){
                ServerSlave slave = new ServerSlave(i, s, server);
                slave.setTimeout(seconds * 1000);
                server.serverSlaves.add(slave);
                i++;
            }
        }catch(IOException e){
            if(e instanceof SocketException){ //a socket has timed out -> the auction has finished
                Offer highestOffer = new Offer("-1", "item0", 0);
                for(Offer tmp : server.offers){
                    if(tmp.compareOffers(highestOffer))
                        highestOffer = tmp;
                }
                System.out.println("Highest offer found: "+highestOffer.toString());
                server.topOffer = highestOffer;
                String winner = highestOffer.getClientID();
                int slaveID = highestOffer.getSlaveID();

                ServerSlave winnerSlave = server.serverSlaves.remove(slaveID); //should return the slave that has placed the offer
                winnerSlave.setAuctionWon(true);
                server.serverSlaves.forEach(slave -> slave.setAuctionWon(false));
            }
        }finally {
            try{
                ss.close();
                for(ServerSlave slave : server.serverSlaves)
                    slave.join();
            }catch(IOException | InterruptedException e2){e2.printStackTrace();}
        }
    }

    protected Offer getTopOffer(){
        return this.topOffer;
    }

    protected synchronized boolean addOffer(Offer offer){
        long timeout = ThreadLocalRandom.current().nextInt(10, 30);
        if(offers.size() == 0)
            topOffer = offer;
        try{
            if(offers.offer(offer, timeout, TimeUnit.MILLISECONDS)) {
                if(offer.compareOffers(topOffer))
                    topOffer = offer;
                return true;
            }
        }catch(InterruptedException ie){
            ie.printStackTrace();
            return false;
        }
        return false;
    }

    protected Item getItemInOffer(){
        return this.itemInOffer;
    }

    protected synchronized LinkedList<Offer> getCurrentOffers(){
        Iterator it = offers.iterator();
        LinkedList<Offer> res = new LinkedList<Offer>();
        it.forEachRemaining((tmp) -> res.add((Offer) tmp));
        return res;
    }

    protected int getAuctionWinner(){
        return this.auctionWinner;
    }

}
