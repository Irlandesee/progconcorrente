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

    private boolean runCondition = true;

    private BlockingQueue<Message> messageQueue;

    public ClientSlaveMaster(Socket _s){
        s = _s;

        messageQueue = new LinkedBlockingQueue<Message>(QUEUE_CAPACITY);
    }

    private void initSlaves(){

    }

    public void exec(){

    }

    private void clearQueue(){

    }

    public boolean getRunCondition(){
        return runCondition;
    }

    public void setRunCondition(boolean _runCondition){
        runCondition = _runCondition;
    }

}
