package com.exams.onlinegame.client;

import java.net.InetAddress;
import java.net.Socket;
import java.io.IOException;
import com.exams.onlinegame.move.Move;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Client extends Thread{

    private int clientID;
    private ClientProxy cp;
    private Socket sock;

    private int numberOfMoves;

    public Client(int numberOfMoves, int clientID){
        this.numberOfMoves = numberOfMoves;
        this.clientID = clientID;
        try{
            sock = new Socket(InetAddress.getLocalHost(), ServerInterface.PORT);
        }catch(IOException ioe){ioe.printStackTrace();}
        this.cp = new ClientProxy(sock, clientID);
    }

    public void run(){
        try{
            System.err.printf("Client %d started, moves: %d\n", clientID, numberOfMoves);
            for(int i = 0; i < numberOfMoves; i++){
                Move mv = new Move(
                        ThreadLocalRandom.current().nextInt(100),
                        ThreadLocalRandom.current().nextInt(100),
                        String.valueOf(clientID));

                System.out.printf("Client %d executing mv: %s\n", clientID, mv);
                cp.next(mv);
            }
            System.err.printf("Client %d quitting\n", clientID);
            cp.quit();
        }catch(IOException io){io.printStackTrace();}
    }

}
