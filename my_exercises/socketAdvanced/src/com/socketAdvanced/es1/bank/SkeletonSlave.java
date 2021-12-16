package com.socketAdvanced.es1.bank;

import com.socketAdvanced.es1.BankInterface.BankInterface;
import com.socketAdvanced.es1.operations.OperationRequest;
import com.socketAdvanced.es1.operations.Result;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SkeletonSlave extends Thread{

    private static int clientID;
    private int skeletonID;
    private Socket sock;
    private Bank bank;

    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    public SkeletonSlave(int skeletonID, Socket sock, Bank bank){
        this.skeletonID = skeletonID;
        this.sock = sock;
        this.bank = bank;
        clientID++;
    }

    public void run(){
        inStream = null;
        try{
            inStream = new ObjectInputStream(sock.getInputStream());
            Object in;
            try{
                while((in = inStream.readObject()) != null){
                    if(in instanceof String){
                        if(in.toString().equals(BankInterface.QUIT))
                            break;
                    }
                    else{
                        OperationRequest op = (OperationRequest) in;
                        Result r = bank.executeOperation(op);
                        System.out.printf("Slave %d writing result %s to socket\n", skeletonID, r.toString());
                        writeResult(r);
                        System.out.println("Done");
                    }
                }
                //quit
                System.out.printf("Slave %d: client %d has dropped the connection\n", skeletonID, clientID);
                sock.close();
            }catch(ClassNotFoundException ce){ce.printStackTrace();}
        }catch(IOException io){
            io.printStackTrace();
        }finally {
            try{
                inStream.close();
            }catch(IOException io2){io2.printStackTrace();}
        }
    }

    private void writeResult(Result r) throws IOException{
        outStream = new ObjectOutputStream(sock.getOutputStream());
        outStream.writeObject(r);
        outStream.flush();
        outStream.close();
    }

}
