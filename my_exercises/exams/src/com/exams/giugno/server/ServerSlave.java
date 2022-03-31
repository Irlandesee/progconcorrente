package com.exams.giugno.server;


import com.exams.giugno.client.ServerInterface;
import com.exams.giugno.resource.Resource;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class ServerSlave extends Thread{

    private int slaveID;
    private Server serv;
    private Socket sock;

    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    public ServerSlave(int slaveID, Socket sock, Server serv){
        this.slaveID = slaveID;
        this.sock = sock;
        this.serv = serv;
        try{
            outStream = new ObjectOutputStream(sock.getOutputStream());
            inStream = new ObjectInputStream(sock.getInputStream());
        }catch(IOException io){
            System.err.printf("Slave %d: error while creating streams\n", slaveID);
            io.printStackTrace();
        }
        this.start();
    }

    public void run(){
        System.err.printf("Slave %d started\n", slaveID);
        boolean runCondition = true;
        while(runCondition && !(sock.isClosed())){
            System.out.println("Waiting for command");
            try{
                String op = inStream.readObject().toString();
                switch(op){
                    case "ADD":
                        while(serv.getCurrentQueueCapacity() == 0){ //queue is full
                            outStream.writeObject(ServerInterface.WAIT);
                            try{
                                Thread.sleep(ThreadLocalRandom.current().nextInt(100, 400));
                            }catch(InterruptedException ie){ie.printStackTrace();}
                        }
                        outStream.writeObject("OK");
                        Resource tmp = (Resource) inStream.readObject();
                        boolean success = serv.addResource(tmp);
                        if(success) outStream.writeObject("OK");
                        else outStream.writeObject("WAIT");
                        outStream.flush();;
                        break;
                    case "REMOVE":
                        while(serv.getCurrentQueueCapacity() == serv.maxCapacity) { //queue is empty
                            outStream.writeObject(ServerInterface.WAIT);
                            try{
                                Thread.sleep(ThreadLocalRandom.current().nextInt(100, 400));
                            }catch(InterruptedException ie){ie.printStackTrace();}
                        }
                        Resource res = serv.removeResource();
                        if(res != null){
                            //System.err.println("Serv returning > "+ res);
                            outStream.writeObject("OK");
                            outStream.writeObject(res);
                        }
                        else outStream.writeObject("WAIT");
                        outStream.flush();;
                        break;
                    case "QUIT":
                        runCondition = false;
                        serv.removeSlave(slaveID);
                        break;
                    default:
                        outStream.writeObject("UNDEFINED");
                        outStream.flush();
                        break;
                }
            }catch(IOException | ClassNotFoundException e){e.printStackTrace();}
        }
        System.err.printf("Slave %d terminating\n", slaveID);
    }

}
