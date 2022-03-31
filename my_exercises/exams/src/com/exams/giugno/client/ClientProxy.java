package com.exams.giugno.client;

import com.exams.giugno.resource.Resource;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ThreadLocalRandom;

public class ClientProxy implements ServerInterface{

    private int proxyID;
    private Socket sock;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    public ClientProxy(int proxyID, Socket sock){
        this.proxyID = proxyID;
        this.sock = sock;
        try{
            outStream = new ObjectOutputStream(sock.getOutputStream());
            inStream = new ObjectInputStream(sock.getInputStream());
        }catch(IOException io){io.printStackTrace();}

    }

    public boolean add(Resource r) throws IOException{
        outStream.writeObject(ServerInterface.ADD);
        outStream.flush();
        String response;
        int sleepTimer = 0;
        final int maxTries = 10;
        try{
            while((response = inStream.readObject().toString()).equals(ServerInterface.WAIT)){
                synchronized (this){
                    try{
                        sleepTimer++;
                        Thread.sleep(ThreadLocalRandom.current().nextInt(100, 400));
                    }catch(InterruptedException ie){
                        ie.printStackTrace();
                    }
                }
                if(sleepTimer == maxTries)
                    break;
            }
            if(response.equals(ServerInterface.OK)){
                outStream.writeObject(r);
                return true;
            }
        }catch(ClassNotFoundException ce){ce.printStackTrace();}
        return false;
    }

    public Resource remove() throws IOException{
        outStream.writeObject(ServerInterface.REMOVE);
        outStream.flush();
        int sleepTimer = 0;
        final int maxTries = 10;
        try{
            while((inStream.readObject().toString()).equals(ServerInterface.WAIT)){
                synchronized (this){
                    try{
                        sleepTimer++;
                        Thread.sleep(ThreadLocalRandom.current().nextInt(100, 400));
                    }catch(InterruptedException ie){ie.printStackTrace();}
                }
                if(sleepTimer == maxTries){
                    System.err.printf("Cp %d reached its max tries\n", proxyID);
                    break;
                }
            }
            if((inStream.readObject().toString()).equals(ServerInterface.OK))
                return (Resource) inStream.readObject();
        }catch(ClassNotFoundException ce){ce.printStackTrace();}
        return null;
    }

    public void quit() throws IOException{
        outStream.writeObject(ServerInterface.QUIT);
        outStream.flush();
    }

}
