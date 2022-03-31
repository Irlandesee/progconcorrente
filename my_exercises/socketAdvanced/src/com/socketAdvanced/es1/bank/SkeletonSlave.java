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
        try{
            outStream = new ObjectOutputStream(sock.getOutputStream());
            inStream = new ObjectInputStream(sock.getInputStream());
        }catch(IOException io){
            System.out.printf("Slave %d: error while creating streams\n", skeletonID);
            io.printStackTrace();
        }
        start();
    }

    public void run(){
        System.err.println("Slave talking with client "+ clientID);
        try{

            try{
                while(true){
                    System.out.println("waiting command");
                    OperationRequest op = (OperationRequest) inStream.readObject();
                    System.err.println("Executing, "+op.toString());
                    if(op.getRequest().equalsIgnoreCase("QUIT")){
                        System.err.printf("Slave %d: client %d has dropped the connection\n", skeletonID, clientID);
                        break;
                    }
                    Result res = bank.executeOperation(op);
                    outStream.writeObject(res);
                    outStream.flush();
                }
            }catch(ClassNotFoundException ce){ce.printStackTrace();}
        }catch(IOException io){
            io.printStackTrace();
        }
        /**
        finally {
            try{
                inStream.close();
                outStream.close();
                sock.close();
            }catch(IOException io2){io2.printStackTrace();}
        }
         **/
    }

}
