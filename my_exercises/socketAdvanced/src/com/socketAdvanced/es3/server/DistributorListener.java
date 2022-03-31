package com.socketAdvanced.es3.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.ThreadLocalRandom;

import com.socketAdvanced.es3.message.Message;
import com.socketAdvanced.es3.client.Client;

public class DistributorListener extends Thread{

    private static int listenerID = 0;
    private Distributor dist;
    private Client c;
    private ObjectInputStream in;
    private boolean listen;
    private boolean runCondition;

    public DistributorListener(Client c, Distributor dist, ObjectInputStream in){
        this.c = c;
        this.in = in;
        this.dist = dist;
        listenerID++;
        listen = false;
        runCondition = true;
        start();
    }

    public void run(){
        System.err.printf("Listener %d started\n", listenerID);
        try{
            Message tmp;
            while(runCondition){
                while(listen == false){
                    synchronized (this){
                        try{
                            System.err.printf("Listener %d waiting\n", listenerID);
                            wait(ThreadLocalRandom.current().nextInt(200, 300));
                        }catch(InterruptedException ie){ie.printStackTrace();}
                    }
                }
                while(!(tmp = (Message) in.readObject()).toString().equalsIgnoreCase("quit")){
                    dist.addMessage(tmp);
                }
                System.out.printf("Listener %d, messages added to the queue\n", listenerID);
            }
        }catch(IOException | ClassNotFoundException io){io.printStackTrace();}
        System.err.printf("Listener %d joining\n", listenerID);
    }

    public void setRunCondition(boolean runCondition){this.runCondition = runCondition;}

}
