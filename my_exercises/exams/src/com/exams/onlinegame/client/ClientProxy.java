package com.exams.onlinegame.client;

import com.exams.onlinegame.move.Move;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class ClientProxy implements ServerInterface{

    private int proxyID;
    private Socket sock;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    private int points;

    public ClientProxy(Socket sock, int proxyID){
        this.sock = sock;
        this.proxyID = proxyID;
        try{
            outStream = new ObjectOutputStream(sock.getOutputStream());
            inStream = new ObjectInputStream(sock.getInputStream());
        }catch(IOException ioe){ioe.printStackTrace();}

        this.points = 0;
    }

    public void next(Move mv) throws IOException{
        outStream.writeObject(ServerInterface.nextMove);
        outStream.writeObject(mv);
        try{
            Thread.sleep(ThreadLocalRandom.current().nextInt(200, 400));
        }catch(InterruptedException ie){ie.printStackTrace();}

        try{
            String str = inStream.readObject().toString();
            if(str.equalsIgnoreCase(ServerInterface.winner)){
                Move winningMove = (Move) inStream.readObject();
                int incomingPoints = (int) inStream.readObject();
                this.points += incomingPoints;
                System.out.printf("Client %d Winner -> %s\n", proxyID, winningMove);
                System.out.printf("Client %d current points: %d\n", proxyID, points);
            }
            else if(str.equalsIgnoreCase(ServerInterface.loser)){
                Move winningMove = (Move) inStream.readObject();
                System.out.printf("Client %d loser\n", proxyID);
            }
        }catch(ClassNotFoundException ce){ce.printStackTrace();}
    }

    public void quit() throws IOException{
        outStream.writeObject(ServerInterface.quit);
    }

}
