package com.asta;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.util.concurrent.ConcurrentHashMap;

public class ServerSlave extends Thread{

    private ServerAsta sa;
    private Socket s;
    protected int id;
    protected int clientID;
    private boolean auctionWon = false;

    private final String QUIT = "QUIT";
    private final String READ = "READ";
    private final String OFFER = "OFFER";
    private final String GET_ITEM = "GET_ITEM";

    public ServerSlave(ServerAsta sa, Socket s, int id){
        this.sa = sa;
        this.s = s;
        this.id = id;
    }

    public int getTimeout(){
        int timeout = -1;
        try{
            timeout = s.getSoTimeout();
        }catch(IOException io){
            System.out.printf("ServerSlave %d: IOE while getting socket timeout\n", id);
            io.printStackTrace();
        }
        return timeout;
    }

    protected boolean setTimeout(int timeout){
        try{
            s.setSoTimeout(timeout);
            return true;
        }catch(IOException io){
            System.out.printf("ServerSlave %d: IOE while setting socket timeout\n", id);
            io.printStackTrace();
            return false;
        }
    }

    protected void closeSocket() throws IOException{s.close();}

    protected Socket getSocket(){return this.s;}

    protected int getClientID(){
        return clientID;
    }

    protected void setClientID(int clientID){
        this.clientID = clientID;
    }

    protected void setActionWon(boolean auctionWon){
        this.auctionWon = auctionWon;
    }

    private boolean checkAuction(){
        return this.auctionWon;
    }

    public void run(){
        String command = "";
        BufferedReader inStream = null;
        PrintWriter outStream = null;
        try{
            inStream = new BufferedReader(new InputStreamReader(s.getInputStream()));
            outStream = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    s.getOutputStream())), true);
            while(!(command = inStream.readLine()).equals(QUIT)){
                switch(command){
                    case READ:
                        System.out.printf("Slave %d: Reading current offers", id);
                        ConcurrentHashMap<String, Double> currentOffers = sa.getCurrentOffers();
                        for(var entry : currentOffers.keySet()){
                            outStream.println(entry + currentOffers.get(entry));
                        }
                        outStream.println("DONE");
                        break;
                    case OFFER:
                        String offer = inStream.readLine();
                        System.out.printf("Slave %d: Placing new offer", id);
                        sa.addOffer(offer);
                        break;
                    case GET_ITEM:
                        String offeredItem = sa.auctionItem;
                        outStream.println(offeredItem);
                        break;
                    default:
                        System.out.printf("Slave %d: Command not recognized", id);
                        outStream.println("UNDEFINED");
                        break;
                }
                //check auction status
                if(checkAuction()){
                    if(sa.getAuctionWinner().equals(this.id)){
                        outStream.println("WON");
                    }
                    else{
                        outStream.println("LOST");
                    }
                }
            }
            // QUIT
            sa.rmSlaveSocket(s);
            s.close();
            System.out.printf("Slave %d closing streams down", id);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }finally{
            try{
                inStream.close();
                outStream.close();
            }catch(IOException ioe2){
                ioe2.printStackTrace();
            }
        }
    }


}
