package com.socketAdvanced.es3.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

import com.socketAdvanced.es3.message.Message;
import com.socketAdvanced.es3.client.Client;

public class DistributorSender extends Thread{

    private static int senderID = 0;
    private LinkedBlockingQueue<Message> messages;
    private ObjectOutputStream outStream;
    private Client c;
    private boolean send;
    private boolean runCondition;

    public DistributorSender(Client c, LinkedBlockingQueue<Message> messages, ObjectOutputStream outStream){
        this.c = c;
        this.messages = messages;
        this.outStream = outStream;
        senderID++;
        send = false;
        runCondition = true;
        start();
    }

    public void run(){
        System.err.printf("Sender %d starting to send messages\n", senderID);
        try{
            while(runCondition){
                while(send == false){
                    synchronized (this){
                        try{
                            wait(ThreadLocalRandom.current().nextInt(200, 300));
                        }catch(InterruptedException ie){ie.printStackTrace();}
                    }
                }
                while(messages.size() > 0){
                    synchronized (messages){
                        outStream.writeObject(messages.poll());
                    }
                }
                System.out.printf("Sender %d messages sent\n", senderID);
            }
        }catch(IOException io){io.printStackTrace();}
        System.out.printf("Sender %d joining\n", senderID);
    }

    public void setRunCondition(boolean runCondition){this.runCondition = runCondition;}

}
