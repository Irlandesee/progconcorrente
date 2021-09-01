package com.chat.client;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.net.Socket;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Scanner;

import java.net.Socket;

import com.chat.message.Message;

public class ClientSlaveWriter extends Thread{
    //producer
    private LinkedBlockingQueue<Message> messageQueue;

    private Scanner sc;
    private PrintWriter outStream;
    private BufferedReader inStream;

    private Socket s;

    private final String SEND = ":y";
	private final String CANCEL = ":n";
    private final String QUIT = ":q";

    private final String ACK_want_to_write = "want_write";
    private final String ACK_want_to_read= "want_read";
    private final String ACK_wait = "wait";
    private final String ACK_read_ok = "begin_read";
    private final String ACK_write_ok = "begin_write";
    private final String ACK_done = "done";

    private boolean runCondition;
    private final int timeout = 1000;

    public ClientSlaveWriter(Socket _s, BlockingQueue<Message> _messageQueue, boolean _runCondition) throws IOException{
        s = _s;
        messageQueue = (LinkedBlockingQueue<Message>) _messageQueue;
        runCondition = _runCondition;
        sc = new Scanner(System.in);
    }

    public void run(){
        while(getRunCondition()) {
            System.out.println("reading user input");
            readUserInput();
            sendMessageQueue();
        }
        if(getRunCondition() == false)
            System.out.printf("Thread slaveWriter %d terminating...", Thread.currentThread().getId());
    }

    private void readUserInput(){
        boolean done = false;
        String userInput = "";
        String choice = "";
        Message msg;
        while(!done){
            System.out.println("Client > ");
            userInput = sc.nextLine();
            System.out.printf("Send message? %s to send, %s to cancel.", SEND, CANCEL);
            choice = sc.nextLine();
            switch(choice){
                case SEND:
                    System.out.println("Writing message to queue");
                    msg = new Message(userInput);
                    try{
                        synchronized (messageQueue){messageQueue.put(msg);}
                    }catch(InterruptedException e){
                        System.out.println("Interrupted! Write not successful!");
                        e.printStackTrace();
                    }
                    break;
                case CANCEL:
                    System.out.println("Message has not been added to queue");
                    break;
                case QUIT:
                    System.out.printf("Thread slaveWriter %d terminating", Thread.currentThread().getId());
                    done = false;
                    setRunCondition(false);
                    break;
            }
        }
    }

    private void sendMessageQueue(){
        System.out.println("Preparing to send objects.");
        String ackString = "";
        boolean done = false;
        outStream.println(ACK_want_to_write);

        while(!done){
            try{
                ackString = inStream.readLine();
                if(ackString.equals(ACK_write_ok)){
                    outStream.println(ACK_wait);
                    try{
                        ObjectOutput objectOutput = new ObjectOutputStream(s.getOutputStream());
                        synchronized (messageQueue){
                            objectOutput.writeObject(messageQueue);
                            done = true;
                        }
                        objectOutput.close();
                    }catch(IOException e1){
                        System.out.println("Error while writing objects to object stream!");
                        e1.printStackTrace();
                    }
                }
                else if(ackString.equals(ACK_wait)){
                    System.out.printf("Thread %d slaveWriter waiting\n",
                    Thread.currentThread().getId(), Client.getServerID());
                    try{
                        wait(timeout);
                    }catch(InterruptedException e2){e2.printStackTrace();}
                }
                else if(ackString.equals(ACK_want_to_read)){
                    //mando ultimi messaggi
                    try{
                        ObjectOutput objectOutput = new ObjectOutputStream(s.getOutputStream());
                        synchronized(messageQueue){
                            objectOutput.writeObject(messageQueue);
                            done = true;
                        }
                        objectOutput.close();
                    }catch(IOException e3){
                        e3.printStackTrace();
                    }
                    //leggi
                    System.out.printf("Thread %d slaveWriter: Server %d wants to read.\n",
                            Thread.currentThread().getId(), Client.getServerID());
                    outStream.println(ACK_read_ok);
                    try{
                        wait(timeout);
                    }catch(InterruptedException e1){
                        System.out.printf("Thread %d slaveWriter waiting for server %d to read",
                                Thread.currentThread().getId(), Client.getServerID());
                        e1.printStackTrace();
                    }
                }
                else if(ackString.equals(ACK_done))
                    continue;
            }catch(IOException e){
                System.out.printf("Thread %d slaveWriter error while reading acknowledgment", Thread.currentThread().getId());
                e.printStackTrace();
            }
            if(!done)
                outStream.println(ACK_want_to_write);
        }
        outStream.println(ACK_done);
        System.out.println("Objects sent");
    }

    public boolean getRunCondition(){return runCondition;}

    public synchronized void setRunCondition(boolean _runCondition){
        runCondition = _runCondition;
    }

    private void printHelp(){

    }

}
