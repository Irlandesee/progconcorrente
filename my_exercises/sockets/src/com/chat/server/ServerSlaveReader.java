package com.chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import java.net.Socket;

import com.chat.message.Message;
import com.chat.server.Server;
import com.chat.server.ServerSlaveMaster;

public class ServerSlaveReader extends Thread{
    //consumer
    private Socket s;

    private LinkedBlockingQueue<Message> messageQueue;
    private BufferedReader inStream;

    private final String QUIT = ":q";
    private boolean runCondition;

    public ServerSlaveReader(Socket _s, BlockingQueue<Message> _messageQueue, boolean _runCondition) throws IOException {
        s = _s;
        messageQueue = (LinkedBlockingQueue<Message>) _messageQueue;
        inStream = new BufferedReader(new InputStreamReader(s.getInputStream()));
        runCondition = _runCondition;
    }

    public void run(){
        try{
            try{
                while(getRunCondition()){
                    String incomingString = inStream.readLine();
                    if(incomingString.equals(QUIT)){
                        System.out.printf("Client %d has disconnected, slave reader thread : %d terminates",
                                Server.getClientID(), Thread.currentThread().getId());
                        //read the remaining messages then quit
                        for(Message m : messageQueue)
                            readMessage(m);
                        setRunCondition(false);
                    }
                    Message msg = new Message(incomingString);
                    readMessage(msg);
                }
            }catch(IOException e1){
                e1.printStackTrace();
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    private synchronized void readMessage(Message msg) throws InterruptedException{
        if(messageQueue.contains(msg)){
            messageQueue.take();
            System.out.println("from client > "+msg.getMessage());
        }
    }

    public boolean getRunCondition(){
        return runCondition;
    }

    private void setRunCondition(boolean _runCondition){
        runCondition = _runCondition;
    }

}