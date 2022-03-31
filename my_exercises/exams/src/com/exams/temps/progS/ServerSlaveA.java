package com.exams.temps.progS;

import java.io.IOError;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import com.exams.temps.progA.ClientInterfaceA;

public class ServerSlaveA extends Thread implements ClientInterfaceA{

    private static int slaveID;
    private int clientID;

    private Socket sock;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    public ServerSlaveA(Socket sock, int clientID){
        this.sock = sock;
        this.clientID = clientID;
        try{
            outStream = new ObjectOutputStream(sock.getOutputStream());
            inStream = new ObjectInputStream(sock.getInputStream());
        }catch(IOException io){io.printStackTrace();}

        this.setName("A"+slaveID+clientID);
        this.start();
    }

    public void run(){

    }

    public void comunicaTemp() throws IOException{

    }

    public void accendiRiscaldamento() throws IOException{

    }

    public void spegniRiscaldamento() throws IOException{

    }

    public void accendiCondizionamento() throws IOException{

    }

    public void spegniCondizionamento() throws IOException{

    }

}
