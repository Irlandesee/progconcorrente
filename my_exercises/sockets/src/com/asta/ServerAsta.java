package com.asta;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class ServerAsta {

    private static final int PORT = 8080;
    private static final int SECONDS = 60;
    private HashMap<Integer, String> items; //0, item0

    private LinkedList<ServerSlave> serverSlaves;
    private LinkedList<Socket> sockets;

    private ConcurrentHashMap<String, Double> offers; //clientID:item double


    private String auctionWinner;
    protected String auctionItem;

    public static void main(String[] args){

        ServerSocket ss = null;
        ServerAsta sa = new ServerAsta();
        sa.initItemsAndOffers();
        sa.initServerSlavesListAndSocketsList();

        try{
            ss = new ServerSocket(PORT);
            ss.setSoTimeout(SECONDS * 1000);
            int i = 0;
            Socket s = null;
            while((s = ss.accept()) != null){
                ServerSlave slave = new ServerSlave(sa, s, i);
                slave.setClientID(i+10);
                if(slave.setTimeout(SECONDS * 1000)){
                    System.out.printf("SS: slave %d timeout set\n", i);
                    sa.addServerSlave(slave);
                    sa.addSocket(s);
                }
                else
                    System.out.println("Error while setting slave timeout");
                i++;
            }
        }catch(IOException e){
            if(e instanceof SocketException){ //timeout
                try{
                    assert ss != null;
                    if(ss.getSoTimeout() == 0){
                        System.out.println("No slaves remaining... closing offer");
                        String itemInOffer = sa.items.get(0);
                        sa.auctionItem = itemInOffer;
                        double biggestOffer = 0;
                        String entryBiggestOffer = "";

                        for(var entry : sa.offers.keySet()){
                            double tmp = sa.offers.get(entry);
                            if(tmp > biggestOffer){
                                biggestOffer = tmp;
                                entryBiggestOffer = entry;
                            }
                        }

                        //find the biggest offer
                        String item = entryBiggestOffer.split(":")[0];
                        double clientID = Double.parseDouble(entryBiggestOffer.split(":")[1]);
                        for(ServerSlave slave : sa.serverSlaves) {
                            if (slave.getClientID() == clientID) {
                                sa.setAuctionWinner(Double.toString(clientID));
                                slave.setActionWon(true);
                                System.out.printf("client %s won the auction for %s for %s\n", Double.toString(clientID), item, Double.toString(biggestOffer));
                            } else {
                                slave.setActionWon(false);
                            }
                            sa.rmServerSlave(slave);
                        }
                    }
                }catch(IOException io){
                    io.printStackTrace();
                }
            }
            e.printStackTrace();
        }finally{
            try{
                ss.close();
            }catch(IOException ie2){
                ie2.printStackTrace();
            }
        }


    }

    private void initItemsAndOffers(){
        items = new HashMap<Integer, String>();
        offers = new ConcurrentHashMap<String, Double>();

        items.put(0, "item0");
        /**
        items.put(1, "item1");
        items.put(2, "item2");
        items.put(3, "item3");
        items.put(4, "item4");
        items.put(5, "item5");
        items.put(6, "item6");
        items.put(7, "item7");
        items.put(8, "item8");
        items.put(9, "item9");
        items.put(10, "item10");
         **/
    }

    private void setAuctionWinner(String auctionWinner){
        this.auctionWinner = auctionWinner;
    }

    protected String getAuctionWinner(){
        return this.auctionWinner;
    }

    private void initServerSlavesListAndSocketsList(){
        serverSlaves = new LinkedList<ServerSlave>();
        sockets = new LinkedList<Socket>();
    }

    private void addServerSlave(ServerSlave ss){
        serverSlaves.add(ss);
    }

    private void addSocket(Socket s){
        sockets.add(s);
    }

    private LinkedList<ServerSlave> getServerSlave(){
        return serverSlaves;
    }

    private LinkedList<Socket> getSocketList(){
        return sockets;
    }

    private boolean rmServerSlave(ServerSlave ss){
        boolean slaveRemoved = false;
        if(serverSlaves.contains(ss.getSocket()))
            slaveRemoved = serverSlaves.remove(ss);
        return slaveRemoved;
    }

    protected boolean rmSlaveSocket(Socket s){
        boolean socketRemoved = false;
        if(sockets.contains(s))
            socketRemoved = sockets.remove(s);
        return socketRemoved;
    }

    public HashMap<Integer, String> getRemainingItemList(){
        return items;
    }


    protected synchronized ConcurrentHashMap<String, Double> getCurrentOffers(){
        return offers;
    }

    /**
     *
     * @param offer -> clientID:item double
     * @return true if added
     */
    protected synchronized void addOffer(String offer){
        String[] tmp = offer.split(" ");
        double offeredAmount = Double.parseDouble(tmp[1]);
        offers.put(tmp[0], offeredAmount);
    }

}
