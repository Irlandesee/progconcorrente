package com.exams.giugno.client;

import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.util.Random;
import com.exams.giugno.resource.Resource;

public class Client extends Thread{

    private int clientID;
    private int runtime;
    private Socket sock;
    private ClientProxy cp;

    public Client(int runtime, int clientID){
        this.runtime = runtime;
        this.clientID = clientID;
        try{
            sock = new Socket(InetAddress.getLocalHost(), ServerInterface.PORT);
        }catch(IOException io){
            io.printStackTrace();
        }
        cp = new ClientProxy(clientID, sock);
        this.start();
    }

    public void run(){
        System.out.printf("Client %d started, runtime left: %d\n", clientID, runtime);

        while(runtime > 0){
            int operation = nextOperation();
            if(operation == 0){ //add
                System.out.printf("Client %d asking server to add a resource\n", clientID);
                try{
                    boolean success = cp.add(new Resource(String.valueOf(this.clientID), "test"));
                    if(success)
                        System.out.printf("Client %d successfully added resource to server\n", clientID);
                    else
                        System.out.printf("Client %d failed to add resource to server\n", clientID);
                }catch(IOException io){
                    io.printStackTrace();
                }

            }
            else if(operation == 1){ //remove
                System.out.printf("Client %d asking server to return a resource\n", clientID);
                Resource r = new Resource("-1", "-1");
                try{
                    r = cp.remove();
                }catch(IOException io){io.printStackTrace();}
                System.out.printf("Client %d successfully received a resource from the server\n", clientID);
                if(r != null) System.err.printf("CP %d from server > %s\n ", clientID,r);
            }
            runtime--;
        }
        System.out.printf("Client %d disconnecting\n", clientID);
        try{cp.quit();}catch(IOException io){io.printStackTrace();}
    }

    private int nextOperation(){
        Random rand = new Random();
        return rand.nextInt(2);
    }


}
