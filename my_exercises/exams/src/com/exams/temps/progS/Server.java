package com.exams.temps.progS;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.LinkedList;

public class Server extends Thread {

    private final int maxUClients = 3;
    private final int maxAClients = 5;

    private int concurrentUClientsConnected;
    private int concurrentAClientsConnected;

    private double currentTempCucina;
    private double currentTempSalotto;
    private double currentTempBagno;
    private double currentTempCameraLetto;

    private ServerSocket ss;

    public Server(){
        try{
            ss = new ServerSocket(9999);
        }catch(IOException io){io.printStackTrace();}
        this.concurrentUClientsConnected = 0;
        this.concurrentAClientsConnected = 0;
        this.setName("Server");
        this.start();
    }

    public void run(){
        Socket sock;
        int clientID = 0;
        LinkedList<ServerSlaveU> slaves = new LinkedList<ServerSlaveU>();
        try{
            while((sock = ss.accept()) != null){
                slaves.add(new ServerSlaveU(sock, clientID));
                clientID++;
            }
        }catch(IOException io){
            io.printStackTrace();
        }
    }

}
