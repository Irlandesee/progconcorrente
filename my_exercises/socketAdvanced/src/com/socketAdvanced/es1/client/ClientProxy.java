package com.socketAdvanced.es1.client;

import com.socketAdvanced.es1.BankInterface.BankInterface;
import com.socketAdvanced.es1.operations.*;
import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ThreadLocalRandom;

public class ClientProxy extends Thread implements BankInterface {

    private static int proxyID;
    private Socket sock;
    private Client client;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private boolean keepAlive = true;


    public ClientProxy(Client c, Socket sock){
        this.sock = sock;
        this.client = c;
        proxyID++;
    }

    public void run(){
        while(getKeepAlive()){
            try{
                System.out.printf("Proxy %d: waiting\n", ClientProxy.proxyID);
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
            }catch(InterruptedException ie){ie.printStackTrace();}

        }
        //quit
        try{
            System.out.println("Cp disconnecting from port@"+BankInterface.PORT);
            disconnect();
        }catch(IOException io2){io2.printStackTrace();}
    }

    public boolean getKeepAlive(){return this.keepAlive;}
    public void setKeepAlive(boolean keepAlive){this.keepAlive = keepAlive;}

    public Result executeOperation(String operation, int amount) throws IOException{
        Result r = null;
        String opReq = null;
        inStream = new ObjectInputStream(sock.getInputStream());
        try{
            OperationRequest dep =
                    new OperationRequest(client.getMyAccountNum(), amount, operation);
            writeRequest(dep);
            r = (Result) inStream.readObject();
            inStream.close();
        }catch(ClassNotFoundException ce){ce.printStackTrace();}
        return r;
    }

    private void writeRequest(OperationRequest op) throws IOException{
        outStream = new ObjectOutputStream(sock.getOutputStream());
        outStream.writeObject(op);
        outStream.flush();
        outStream.close();
    }

    public void quit(){setKeepAlive(false);}

    private void disconnect() throws IOException{
        outStream = new ObjectOutputStream(sock.getOutputStream());
        outStream.writeObject(BankInterface.QUIT);
        outStream.flush();
        outStream.close();
    }

}
