package com.exams.temps.progA;

import com.exams.temps.command.Command;

import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;

public class ClientA extends Thread{

    private final String typeClient = "A";
    private static int clientID = 0;
    private Socket sock;
    private ClientProxyA cp;

    public ClientA(){
        this.setName(typeClient+","+clientID);

        try{
            sock = new Socket(InetAddress.getLocalHost(), ClientInterfaceA.PORT);
            cp = new ClientProxyA(sock, clientID);
        }catch(IOException io){io.printStackTrace();}
        clientID++;
        this.start();
    }

    public void run(){
        Command cmd = new Command("testcommand", "testroom", typeClient);
        try {
            cp.writeCommand(cmd);
        }catch(IOException io){
            io.printStackTrace();
        }
    }

}
