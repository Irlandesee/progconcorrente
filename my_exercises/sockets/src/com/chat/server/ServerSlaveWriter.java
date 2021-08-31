package com.chat.server;

import java.io.*;

import java.net.Socket;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Scanner;

import com.chat.message.Message;

public class ServerSlaveWriter extends Thread{
    //producer
    private Socket s;

    private LinkedBlockingQueue<Message> messageQueue;
    private Scanner sc;

	private final String SEND = ":y";
	private final String CANCEL = ":n";
    private final String QUIT = ":q";

    private boolean runCondition;

    public ServerSlaveWriter(Socket _s, BlockingQueue<Message> _messageQueue, boolean _runCondition) throws  IOException{
        s = _s;
        messageQueue = (LinkedBlockingQueue<Message>) _messageQueue;
        sc = new Scanner(System.in);
        runCondition = _runCondition;
    }

    public void run(){

    }

    private void readUserInput(){
        boolean done = false;
        String userInput = "";
        String choice = "";
        Message msg;
        while(!done){
            System.out.println("Server > ");
            userInput = sc.nextLine();
            System.out.printf("Send message? %s to send, %s to cancel.", SEND, CANCEL);
            choice = sc.nextLine();
            switch(choice){
                case SEND:
                    System.out.println("writing message to queue");
                    msg = new Message(userInput);
                    try{
                        synchronized (messageQueue){messageQueue.put(msg);}
                        userInput = "";
                    }catch(InterruptedException e){
                        System.out.println("Interrupted! write not successful!");
                        e.printStackTrace();
                    }
                    break;
                case CANCEL:
                    System.out.println("Message has not been added to queue");
                    userInput = "";
                    break;
                case QUIT:
                    System.out.printf("Thread slaveWriter %d terminating", Thread.currentThread().getId());
                    done = false;
                    setRunCondition(false);
                    break;
            }
            if(messageQueue.size() > 0){
                try{
                    sendMessageQueue();
                    //messageQueue.clear();
                    notify();
                }catch(InterruptedException e){
                    System.out.printf("Thread slaveWriter %d interrupted while sending queue", Thread.currentThread().getId());
                    e.printStackTrace();
                }
            }
        }
    }

    private synchronized void sendMessageQueue() throws InterruptedException{
        //https://stackoverflow.com/questions/8894760/notify-a-thread-in-the-client-that-was-send-on-wait-in-the-server
        try{
            ObjectOutput objectOutput = new ObjectOutputStream(s.getOutputStream());
            objectOutput.writeObject(messageQueue);
            objectOutput.close();
        }catch(IOException e){
            System.out.println("Error while writing objects to object stream!");
            e.printStackTrace();
        }
    }

    public boolean getRunCondition(){
        return runCondition;
    }

    private void setRunCondition(boolean _runCondition){
        runCondition = _runCondition;
    }

    private void printHelp(){

    }

}