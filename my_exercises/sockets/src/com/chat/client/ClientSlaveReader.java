package com.chat.client;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import java.net.Socket;

import com.chat.message.Message;

public class ClientSlaveReader extends Thread{
    //consumer
    private Socket s;

    private LinkedBlockingQueue<Message> messageQueue;
    private PrintWriter outStream;
    private BufferedReader inStream;

    private final String QUIT = ":q";

    private final String ACK_want_to_read = "want_read";
    private final String ACK_want_to_write = "want_write";
    private final String ACK_wait = "wait";
    private final String ACK_read_ok = "begin_read";
    private final String ACK_write_ok = "begin_write";
    private final String ACK_done = "done";

    private final int timeout = 1000;

    private boolean runCondition;

    public ClientSlaveReader(Socket _s, BlockingQueue<Message> _messageQueue, boolean _runCondition) throws  IOException{
        s = _s;
        messageQueue = (LinkedBlockingQueue<Message>) _messageQueue;
        runCondition = _runCondition;

        outStream = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
        inStream = new BufferedReader(new InputStreamReader(s.getInputStream()));

    }

    public void run(){
        String ackString = "";
        try{
            while(getRunCondition()){
                readMessageQueue();
                //take one message from the queue and read it
                Message m = messageQueue.take();
                String message = m.getMessage();
                if(message.equals(QUIT)){
                    System.out.printf("Server %d has disconnected from the chat\n", Client.getServerID());
                    if(messageQueue.size() > 0){
                        for(Message tmp : messageQueue)//there are still messages to be read
                            System.out.printf("Server %d says > %s\n", Client.getServerID(), tmp.getMessage());
                    }
                    setRunCondition(false);
                }else
                    System.out.printf("Server %d says > %s\n", Client.getServerID(), m.getMessage());
            }
        }catch(InterruptedException e){
            System.out.printf("Thread slaveReader %d interrupted, terminating...", Thread.currentThread().getId());
            e.printStackTrace();
        }
    }

    private void readMessageQueue(){
        //https://stackoverflow.com/questions/8894760/notify-a-thread-in-the-client-that-was-send-on-wait-in-the-server
        System.out.println("Preparing to receive objects");
        String ackString = "";
        boolean done = false;
        outStream.println(ACK_want_to_read);

        while(!done){
            try{
                ackString = inStream.readLine();
                if(ackString.equals(ACK_read_ok)){//posso leggere
                    outStream.println(ACK_wait);//leggo
                    try{
                        ObjectInput objectInput = new ObjectInputStream(s.getInputStream());
                        try{
                            synchronized (messageQueue){
                                messageQueue = (LinkedBlockingQueue<Message>) objectInput.readObject();
                                done = true;
                            }
                            objectInput.close();
                        }catch (ClassNotFoundException e1) {e1.printStackTrace();}
                    }catch(IOException e) {
                        System.out.println("Error while reading objects from stream!");
                        e.printStackTrace();
                    }
                }
                else if(ackString.equals(ACK_wait)){//aspetto => puoi scrivere
                    System.out.printf("Thread %d slaveReader waiting\n", Thread.currentThread().getId());
                    outStream.println(ACK_write_ok);//scrivi
                    try{
                        wait(timeout);
                    }catch(InterruptedException e2){e2.printStackTrace();}
                }
                else if(ackString.equals(ACK_want_to_write)){//vuole scrivere
                    //copio
                    try{
                        ObjectInput objectInput = new ObjectInputStream(s.getInputStream());
                        synchronized (messageQueue){
                            messageQueue = (LinkedBlockingQueue<Message>) objectInput.readObject();
                            done = true;
                        }
                        objectInput.close();
                    }catch(ClassNotFoundException e2){e2.printStackTrace();}
                    //scrivi
                    System.out.printf("Thread %d slaveReader: server %d wants to write\n", Thread.currentThread().getId(), Client.getServerID());
                    outStream.println(ACK_write_ok);
                    try{
                        wait(timeout);
                    }catch(InterruptedException e3){
                        System.out.printf("Thread %d slaveReader waiting for server %d to write\n",
                                Thread.currentThread().getId(), Client.getServerID());
                        e3.printStackTrace();
                    }
                }
                else if(ackString.equals(ACK_done))
                    continue;
            }catch(IOException e){
                System.out.printf("Thread slaveReader %d error while reading acknowledgment\n", Thread.currentThread().getId());
                e.printStackTrace();
            }
            if(!done)
                outStream.println(ACK_want_to_read);
        }
        outStream.println(ACK_done);
        System.out.println("Objects read.");
    }

    public boolean getRunCondition(){return runCondition;}

    public void setRunCondition(boolean _runCondition){
        runCondition = _runCondition;
    }

}
