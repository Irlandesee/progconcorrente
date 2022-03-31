package com.exams.onlinegame.server;

import com.exams.onlinegame.move.Move;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Server extends Thread{

    private ServerSocket ss;
    private final int PORT = 9999;

    private LinkedBlockingQueue<ServerSlave> slaves;
    private LinkedBlockingQueue<Move> moves;

    private WinnerChecker checker;
    private Move currentWinningMove;

    public Server(){

        moves = new LinkedBlockingQueue<Move>();
        slaves = new LinkedBlockingQueue<ServerSlave>();
        currentWinningMove = new Move(ThreadLocalRandom.current().nextInt(100),
                ThreadLocalRandom.current().nextInt(100), "initialmove");
        checker = new WinnerChecker(this);
        try{
            ss = new ServerSocket(PORT);
        }catch(IOException io){io.printStackTrace();}
        checker.start();
        this.setName("Server");
    }

    protected synchronized void nextMove(Move mv){
        try{
            moves.put(mv);
        }catch(InterruptedException ie){ie.printStackTrace();}
    }

    protected synchronized void setWinningMove(Move mv){
        this.currentWinningMove = mv;
    }

    protected synchronized Move getCurrentWinningMove(){
        return this.currentWinningMove;
    }

    protected synchronized Move getMove(){
        return moves.poll();
    }

    protected synchronized int getQueueSize(){
        return moves.size();
    }

    protected synchronized void removeSlave(ServerSlave slave){
        slaves.remove(slave);
        System.out.printf("Server: removed slave %s, slaves now active %d\n", slave.getName(), slaves.size());
    }

    public void run(){
        int i = 0;
        Socket sock;
        System.err.printf("%s launched and ready\n", this.getName());
        try{
            while((sock = ss.accept()) != null){
                ServerSlave slave = new ServerSlave(sock, this, i);
                i++;
                try{
                    slaves.put(slave);
                }catch(InterruptedException ie){ie.printStackTrace();}
                System.out.println("New connection accepted, slaves now active: "+slaves.size());
            }
            checker.setRuncondition(false);
        }catch(IOException io){io.printStackTrace();}

    }

}
