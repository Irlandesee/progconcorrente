package com.exams.onlinegame.server;

import com.exams.onlinegame.move.Move;

import java.io.EOFException;
import java.util.concurrent.ThreadLocalRandom;
import java.io.IOException;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ServerSlave extends Thread{

    private int slaveID;
    private Server serv;
    private Socket sock;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    public ServerSlave(Socket sock, Server serv, int slaveID){
        this.sock = sock;
        this.slaveID = slaveID;
        this.serv = serv;
        try{
            outStream = new ObjectOutputStream(sock.getOutputStream());
            inStream = new ObjectInputStream(sock.getInputStream());
        }catch(IOException ioe){ioe.printStackTrace();}

        this.setName("Slave"+slaveID);
        this.start();
    }

    public void run(){
        try{
            String cmd;
            Move mv;
            while(!(cmd = inStream.readObject().toString()).equals("QUIT")){
                if(cmd.equals("NEXT")){
                    mv = (Move) inStream.readObject();
                    serv.nextMove(mv);
                    try{
                        Thread.sleep(ThreadLocalRandom.current().nextInt(300, 400));
                    }catch(InterruptedException ie){ie.printStackTrace();}
                    Move currentWinningMove = serv.getCurrentWinningMove();
                    String winner = currentWinningMove.getStr();
                    if(winner.equals(mv.getStr())){
                        outStream.writeObject("WINNER");
                        outStream.writeObject(currentWinningMove);
                        outStream.writeObject(10); //bonus points
                    }
                    else{
                        outStream.writeObject("LOSER");
                        outStream.writeObject(currentWinningMove);
                    }
                }
                else{
                    outStream.writeObject("UNDEFINED");
                }
            }
            System.out.printf("Client has quit, slave %d terminating\n", slaveID);
            serv.removeSlave(this);
        }catch(IOException ioe){
            ioe.printStackTrace();
            System.out.printf("Client has disconnected, slave %d terminating\n", slaveID);
        }catch(ClassNotFoundException ce){
            ce.printStackTrace();
        }
    }

}
