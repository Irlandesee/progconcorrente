package com.socketAdvanced.es1.client;

import com.socketAdvanced.es1.BankInterface.BankInterface;
import com.socketAdvanced.es1.operations.*;
import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ThreadLocalRandom;

public class ClientProxy implements BankInterface {

    private static int proxyID;
    private Socket sock;
    private Client client;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    private Result result;

    public ClientProxy(Client c, Socket sock){
        this.sock = sock;
        this.client = c;
        proxyID++;
        try{
            outStream = new ObjectOutputStream(sock.getOutputStream());
            inStream = new ObjectInputStream(sock.getInputStream());
        }catch(IOException io){
            io.printStackTrace();
            System.err.printf("Proxy: %d error while creating streams\n", proxyID);
        }
    }

    public void executeOperation(String operation, int amount){
        Result r = new Result(client.getMyAccountNum(), amount, operation, false);
        try{

            System.out.printf("Proxy: Executing operation: %s %s\n", operation, amount);
            OperationRequest dep = new OperationRequest(client.getMyAccountNum(), amount, operation);System.out.println("Proxy: Writing object to stream");
            outStream.writeObject(dep);
            outStream.flush();
            ClientSlave cs = new ClientSlave(inStream, r);
            try{
                Thread.sleep(ThreadLocalRandom.current().nextInt(100, 200));
            }catch(InterruptedException ie){ie.printStackTrace();}
            setResult(cs.getRes());
        }catch(IOException ioe){ioe.printStackTrace();}
    }

    public void quit() throws IOException{
        System.out.println("Cp disconnecting from port@"+BankInterface.PORT);
        outStream = new ObjectOutputStream(sock.getOutputStream());
        outStream.writeObject(BankInterface.QUIT);
        outStream.flush();
        outStream.close();
    }

    private void setResult(Result r){
        this.result = r;
    }

    public Result getResult(){return this.result;}

}
