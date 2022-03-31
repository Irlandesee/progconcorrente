package com.socketAdvanced.es3.server;

import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.LinkedList;
import com.socketAdvanced.es3.client.Client;
import com.socketAdvanced.es3.message.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class Distributor extends Thread{

    private final int messageThreshold = 5;
    private int maxClients;
    private int currentMessagesReceived;
    private LinkedBlockingQueue<Client> clients;
    private LinkedBlockingQueue<Message> messages;

    private LinkedList<DistributorListener> listeners;
    private LinkedList<DistributorSender> senders;

    private Socket sock;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    public Distributor(int maxClients, Socket sock){
        this.maxClients = maxClients;
        this.sock = sock;
        clients = new LinkedBlockingQueue<Client>(maxClients);
        messages = new LinkedBlockingQueue<Message>();
        listeners = new LinkedList<DistributorListener>();
        senders = new LinkedList<DistributorSender>();
        try{
            outStream = new ObjectOutputStream(sock.getOutputStream());
            inStream = new ObjectInputStream(sock.getInputStream());
        }catch(IOException io){
            System.err.println("Distributor: error while creating streams");
            io.printStackTrace();
        }
        start();
    }

    public void run(){
        System.err.println("Distributor started");
        currentMessagesReceived = 0;
        while(clients.size() == 0){
            try{
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
            }catch(InterruptedException ie){ie.printStackTrace();}
        }
        while(clients.size() > 0){
            //listen
            while(currentMessagesReceived < messageThreshold){
                try{
                    Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
                }catch(InterruptedException ie){ie.printStackTrace();}
            }
            //send


            //reset messages queue
            listeners.clear();
            messages.clear();
            System.out.println("Distributor: messages sent");
            System.out.println("Clients still connected: "+clients.size());
        }
        //end

    }

    public synchronized boolean addClient(Client c){
        if(!(clients.size() == maxClients)){
            clients.offer(c);
            listeners.add(new DistributorListener(c,this, inStream));
            senders.add(new DistributorSender(c, messages, outStream));
            return true;
        }
        return false;
    }

    public synchronized boolean rmClient(Client c){
        if(clients.contains(c)){
            clients.remove(c);
            //remove  both listener and sender associated with the client
        }
        return false;
    }

    public synchronized void addMessage(Message msg){
        currentMessagesReceived++;
        messages.offer(msg);
    }

    public synchronized void rmMessageCount(){currentMessagesReceived--;}
}
