package com.chat.server;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.chat.message.Message;

public class ServerSlaveMaster{

    private static final short QUEUE_CAPACITY = 100;
    private static final short NUMBERof_SLAVE_READERS = 1;
    private static final short NUMBERof_SLAVE_WRITERS = 1;

    private Socket s;
    private int clientID;

    private ServerSlaveReader slaveReader;
    private ServerSlaveWriter slaveWriter;

    private BlockingQueue<Message> messageQueue;

    private boolean runCondition = true;

    public ServerSlaveMaster(Socket _s){
        s = _s;
        clientID = Server.getClientID();

        messageQueue = new LinkedBlockingQueue<Message>(QUEUE_CAPACITY);
    }

    private void initSlaves(){
        try{
            slaveReader = new ServerSlaveReader(s, messageQueue, true);
            slaveWriter = new ServerSlaveWriter(s, messageQueue, true);

            slaveReader.start();
            slaveWriter.start();
        }catch(IOException e){
            System.out.println("IOException while initializing slaves!");
            e.printStackTrace();
        }
    }

    public void exec(){
        initSlaves();
        while(getRunCondition()){
            if(!slaveReader.getRunCondition())
                slaveReader.interrupt();
            else if(!slaveWriter.getRunCondition())
                slaveWriter.interrupt();
        }
        if(!getRunCondition()){
            try{
                s.close();
            }catch(IOException e){
                System.out.printf("Error while closing the socket! %d",
                        Thread.currentThread().getId());
                e.printStackTrace();
            }
        }
    }

    private void clearQueue(){
        messageQueue.clear();
    }

    private boolean getRunCondition(){
        return runCondition;
    }

    private void setRunCondition(boolean _runCondition){
        runCondition = _runCondition;
    }

}