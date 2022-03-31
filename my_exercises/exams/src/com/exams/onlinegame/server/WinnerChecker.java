package com.exams.onlinegame.server;

import java.util.concurrent.ThreadLocalRandom;
import com.exams.onlinegame.move.Move;


public class WinnerChecker extends Thread{

    private Server serv;
    private boolean runcondition;

    public WinnerChecker(Server serv){
        this.serv = serv;
        this.runcondition = true;
    }

    public void setRuncondition(boolean runcondition){this.runcondition = runcondition;}

    public void run(){
        System.out.println("Checker started");
        try{
            while(runcondition){
                //System.out.println("queue size: "+serv.getQueueSize());
                while(serv.getQueueSize() > 0){
                    Move mv = serv.getMove();
                    if(ThreadLocalRandom.current().nextBoolean()){
                        serv.setWinningMove(mv);
                        System.err.println("New winner set: "+mv);
                    }
                }
                Thread.sleep(ThreadLocalRandom.current().nextInt(100, 200));
            }
            if(runcondition == false)
                System.out.println("Checker terminating");
        }catch(InterruptedException ie){ie.printStackTrace();}
    }

}
