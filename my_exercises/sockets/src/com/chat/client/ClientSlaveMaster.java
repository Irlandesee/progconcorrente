package com.chat.client;

import java.net.InetAddress;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.chat.message.Message;

public class ClientSlaveMaster{

    private static final short QUEUE_CAPACITY = 100;
    private static final short NUMBERof_SLAVE_READERS = 1;
    private static final short NUMBERof_SLAVE_WRITERS = 1;

    private Socket s;
    private int serverID;

    private ClientSlaveReader slaveReader;
    private ClientSlaveWriter slaveWriter;

    private boolean runCondition = true;

    private BlockingQueue<Message> messageQueue;

    public ClientSlaveMaster(Socket _s){
        s = _s;
        serverID = Client.getServerID();
        messageQueue = new LinkedBlockingQueue<Message>(QUEUE_CAPACITY);
    }

    private void initSlaves(){
        try{
            slaveReader = new ClientSlaveReader(s, messageQueue, true);
            slaveWriter = new ClientSlaveWriter(s, messageQueue, true);

            slaveReader.start();
            slaveWriter.start();

        }catch(IOException e){
            System.out.println("IOException while initializing slaves!");
            e.printStackTrace();
        }
    }

    public void exec(){
        initSlaves();
        /**
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
        }**/
    }

    private void clearQueue(){
        messageQueue.clear();
    }

    public boolean getRunCondition(){
        return runCondition;
    }

    public void setRunCondition(boolean _runCondition){
        runCondition = _runCondition;
    }

}
