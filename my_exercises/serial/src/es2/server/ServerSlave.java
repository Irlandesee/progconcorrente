package es2.server;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ServerSlave extends Thread{

    private int slaveID;
    private Socket sock;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private int clientID;
    private boolean auctionWon = false;


    public ServerSlave(int slaveID, Socket sock){
        this.slaveID = slaveID;
        this.sock = sock;
    }

    public void run(){
        String command  = "";
        ObjectInputStream inStream = null;
        ObjectOutputStream outStream = null;
        try{
            inStream = new ObjectInputStream(sock.getInputStream());
            while(!(command = inStream.readObject().toString()).equals(Server.QUIT)){
                
            }
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }finally{

        }
    }

    public int getTimeout(){
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
