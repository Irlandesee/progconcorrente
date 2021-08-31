package com.chat.server;

import java.io.IOException;

import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import java.net.Socket;

import com.chat.message.Message;

public class ServerSlaveReader extends Thread{
    //consumer
    private Socket s;

    private LinkedBlockingQueue<Message> messageQueue;

    private final String QUIT = ":q";
    private boolean runCondition;

    public ServerSlaveReader(Socket _s, BlockingQueue<Message> _messageQueue, boolean _runCondition) throws IOException {
        s = _s;
        messageQueue = (LinkedBlockingQueue<Message>) _messageQueue;
        runCondition = _runCondition;
    }

    public void run(){
        try{
            while(getRunCondition()){
                readMessageQueue();
                //take one message from the queue and read it
                Message m = messageQueue.take();
                String message = m.getMessage();
                if(message.equals(QUIT)){
                    System.out.printf("Client %d has disconnected from the chat\n", Server.getClientID());
                    if(messageQueue.size() > 0){ //there are still messages to be read
                        for(Message tmp : messageQueue)
                            System.out.printf("Client %d says > %s\n", Server.getClientID(), tmp.getMessage());
                    }
                    setRunCondition(false);
                }else{
                    System.out.printf("Client %d says > %s\n", Server.getClientID(), m.getMessage());
                }
            }
        }catch(InterruptedException e){
            System.out.printf("Thread slaveReader %d interrupted, terminating...", Thread.currentThread().getId());
            e.printStackTrace();
        }
    }

    private synchronized void readMessageQueue(){
        //https://stackoverflow.com/questions/8894760/notify-a-thread-in-the-client-that-was-send-on-wait-in-the-server
        System.out.println("Starting object read");
        try{
            ObjectInput objectInput = new ObjectInputStream(s.getInputStream());
            try{
                messageQueue = (LinkedBlockingQueue<Message>) objectInput.readObject();
            }catch(ClassNotFoundException e1){
                e1.printStackTrace();
            }
            objectInput.close();
        }catch(IOException e){
            System.out.println("Error while reading objects from Stream!");
            e.printStackTrace();
        }
        System.out.println("Done");
    }

    public boolean getRunCondition(){
        return runCondition;
    }

    private void setRunCondition(boolean _runCondition){
        runCondition = _runCondition;
    }

}