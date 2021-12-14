package es2.server;

import es2.offer.Offer;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Iterator;

public class ServerSlave extends Thread{

    protected int slaveID;
    private Socket sock;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private Server serv;
    private int clientID;
    private boolean auctionWon = false;

    public ServerSlave(int slaveID, Socket sock, Server serv){
        this.slaveID = slaveID;
        this.sock = sock;
        this.serv = serv;
    }

    public void run(){
        String commandReceived  = "";
        try{
            inStream = new ObjectInputStream(sock.getInputStream());
            while(!(commandReceived = inStream.readObject().toString()).equals(Server.QUIT)){
                switch(commandReceived){
                    case Server.READ:
                        read();
                        break;
                    case Server.OFFER:
                        offer((Offer) inStream.readObject());
                        break;
                    case Server.GET_ITEM:
                        getItem();
                        break;
                    case Server.GET_OFFERS:
                        getOffers();
                        break;
                    default:
                        System.out.println("Command not recognized");
                        break;
                }
                //check auction status
                if(checkAuction()){
                    if(serv.getAuctionWinner() == clientID){
                        outStream = new ObjectOutputStream(sock.getOutputStream());
                        outStream.writeObject("WON");
                        outStream.flush();
                        outStream.close();
                    }
                    else{
                        outStream = new ObjectOutputStream(sock.getOutputStream());
                        outStream.writeObject("LOST");
                        outStream.flush();
                        outStream.close();
                    }
                }
            }
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            try{
                //quit
                System.out.printf("Client %d has closed the connection\n", clientID);
                System.out.printf("Slave %d closing socket\n", slaveID);
                sock.close();
                inStream.close();
            }catch(IOException e2){e2.printStackTrace();}
        }
    }

    private void read() throws IOException{
        System.out.printf("Slave %d: reading top offer\n", slaveID);
        outStream = new ObjectOutputStream(sock.getOutputStream());

        //TODO: implementare ricerca top offerta -> sends back the amount offered

        //System.out.println("Done");
        outStream.flush();
        outStream.close();
    }

    private void offer(Offer offer) throws IOException{
        System.out.printf("Slave %d placing new offer\n", slaveID);
        outStream = new ObjectOutputStream(sock.getOutputStream());
        offer.setSlaveID(slaveID);
        if(serv.addOffer(offer)) {
            System.out.printf("Slave: %d, success in placing offer\n", slaveID);
            outStream.writeObject(Server.OK);
        }
        else{
            System.out.printf("Slave: %d, failure in placing offer\n", slaveID);
            outStream.writeObject(Server.KO);
        }
        outStream.flush();
        outStream.close();
    }

    private void getItem() throws IOException{
        System.out.printf("Slave %d retrieving item in offer\n", slaveID);
        outStream = new ObjectOutputStream(sock.getOutputStream());

        outStream.writeObject(serv.getItemInOffer());
        System.out.println("Done");

        outStream.flush();
        outStream.close();
    }

    private void getOffers() throws IOException{
        System.out.printf("Slave %d retrieving current offers\n", slaveID);
        outStream = new ObjectOutputStream(sock.getOutputStream());

        LinkedList<Offer> currentOffers = serv.getCurrentOffers();
        Iterator it = currentOffers.iterator();

        it.forEachRemaining((tmp) -> {
            try {outStream.writeObject(tmp);} catch (IOException e) {
                try{outStream.writeObject("KO");}catch(IOException e2){e2.printStackTrace();}
                e.printStackTrace();
            }
        });
        //System.out.println("Done");
        outStream.writeObject("OK");
        outStream.flush();
        outStream.close();
    }

    private void quit() throws IOException{
        System.out.printf("Slave %d quitting\n", slaveID);
        outStream = new ObjectOutputStream(sock.getOutputStream());
        outStream.writeObject(Server.QUIT);
        outStream.flush();
        outStream.close();
    }

    protected int getTimeout(){
        int timeout = -1;
        try{
            timeout = sock.getSoTimeout();
        }catch(IOException io){io.printStackTrace();}
        return timeout;
    }

    protected boolean setTimeout(int timeout){
        try{
            sock.setSoTimeout(timeout);
            return true;
        }catch(IOException io){
            io.printStackTrace();
            return false;
        }
    }

    protected void closeSocket(){
        try{sock.close();}
        catch(IOException io){
            System.out.printf("Server Slave %d, IOException while closing socket\n", this.slaveID);
            io.printStackTrace();}
    }

    protected int getClientID(){return this.clientID;}
    protected void setClientID(int clientID){this.clientID = clientID;}
    protected void setAuctionWon(boolean auctionWon){this.auctionWon = auctionWon;}
    private boolean checkAuction(){return this.auctionWon;}

}
