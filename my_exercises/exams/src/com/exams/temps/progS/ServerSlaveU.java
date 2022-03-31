package com.exams.temps.progS;

import com.exams.temps.command.Command;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ServerSlaveU extends Thread{

    private static int slaveID = 0;
    private String slaveName;
    private int clientID;
    private Socket sock;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;


    public ServerSlaveU(Socket sock, int clientID){
        this.sock = sock;
        this.clientID = clientID;

        try{
            outStream = new ObjectOutputStream(sock.getOutputStream());
            inStream = new ObjectInputStream(sock.getInputStream());
        }catch(IOException io){io.printStackTrace();}
        this.setName("Slave"+slaveID);
        slaveID++;
        this.start();
    }

    public void run(){
        Command cmd;
        try{
            while((cmd = (Command) inStream.readObject()) != null){
                System.out.printf("%s riceve: %s\n", this.getName(), cmd);
            }
        }catch(IOException | ClassNotFoundException e){e.printStackTrace();}
    }

}
