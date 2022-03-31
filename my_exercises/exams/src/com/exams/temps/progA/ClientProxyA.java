package com.exams.temps.progA;

import com.exams.temps.command.Command;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ClientProxyA{

    private Socket sock;
    private int proxyID;

    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    public ClientProxyA(Socket sock, int proxyID){
        this.sock = sock;
        this.proxyID = proxyID;
        try{
            outStream = new ObjectOutputStream(sock.getOutputStream());
            inStream = new ObjectInputStream(sock.getInputStream());
        }catch(IOException io){io.printStackTrace();}
    }

    public void writeCommand(Command cmd) throws IOException{
        outStream.writeObject(cmd);
        outStream.flush();
    }



}
