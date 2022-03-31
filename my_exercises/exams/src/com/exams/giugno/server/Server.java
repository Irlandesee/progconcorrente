package com.exams.giugno.server;
import com.exams.giugno.resource.Resource;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.IOException;

public class Server extends Thread{
    private final int port = 9999;
    protected final int maxCapacity = 5;
    private LinkedBlockingQueue<Resource> resourceList;
    private ConcurrentHashMap<Integer, ServerSlave> slaves;

    private ServerSocket ss;

    public Server(){
        resourceList = new LinkedBlockingQueue<Resource>(maxCapacity);
        slaves = new ConcurrentHashMap<Integer, ServerSlave>();
        try{
            ss = new ServerSocket(port);
        }
        catch(IOException io){System.err.println("Error while creating server socket");}
        this.start();
    }


    public synchronized boolean addResource(Resource r){
        return resourceList.add(r);
    }

    public synchronized Resource removeResource(){
        return resourceList.poll();
    }

    public void addSlave(int id, ServerSlave slave){
        slaves.put(id, slave);
    }

    public synchronized void removeSlave(int id){
        slaves.remove(id);
    }

    public synchronized int getCurrentQueueCapacity(){
        return resourceList.remainingCapacity();
    }

    public void run(){
        Socket s;
        int i = 0;
        try{
            while((s = ss.accept()) != null){
                ServerSlave slave = new ServerSlave(i, s, this);
                addSlave(i, slave);
                System.out.println("Server> new connection accepted and new slave created");
                i++;
            }
        }catch(IOException io){
            io.printStackTrace();
        }
    }

}
