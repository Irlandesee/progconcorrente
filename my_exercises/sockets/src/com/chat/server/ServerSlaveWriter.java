package com.chat.server;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import java.net.Socket;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Scanner;

import com.chat.message.Message;
import com.chat.server.Server;
import com.chat.server.ServerSlaveMaster;

public class ServerSlaveWriter extends Thread{
    //producer
    private Socket s;

    private LinkedBlockingQueue<Message> messageQueue;
    private PrintWriter outStream;
    private Scanner sc;

	private final String SEND = ":y";
	private final String CANCEL = ":n";
    private final String QUIT = ":q";

    private boolean runCondition;

    public ServerSlaveWriter(Socket _s, BlockingQueue<Message> _messageQueue, boolean _runCondition) throws  IOException{
        s = _s;
        messageQueue = (LinkedBlockingQueue<Message>) _messageQueue;
        outStream = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
        sc = new Scanner(System.in);

        runCondition = _runCondition;

    }

    public void run(){
        try{
            while(getRunCondition()){
                System.out.print("Server > ");
                String typedString = sc.nextLine();
                System.out.printf("Send message? %s, %s", SEND, CANCEL);
                String choice = sc.nextLine();

                Message msg;
                switch (choice) {
                    case SEND:
                        System.out.println("Writing message to queue");
                        msg = new Message(typedString);
                        writeMessage(msg);
                        break;
                    case CANCEL:
                        System.out.println("Message not sent");
                        typedString = "";
                        continue;
                    case QUIT:
                        System.out.println("terminating");
                        setRunCondition(false);
                        break;
                }
                outStream.println(messageQueue.take());
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    private synchronized void writeMessage(Message msg) throws InterruptedException{
        messageQueue.put(msg);
    }

    public boolean getRunCondition(){
        return runCondition;
    }

    private void setRunCondition(boolean _runCondition){
        runCondition = _runCondition;
    }

}