package com.filosofi.es1;

import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread{

    private String id;
    private Chopstick left;
    private Chopstick right;
    private Table t;

    private int numOfChopsticks = 0; //numero di chopsticks "in mano" in questo momento

    private String[] actions = {"is thinking", "is hungry", "is eating"};

    public enum PhilosopherState {
        thinking, //waiting
        hungry, //ready
        eating //running
    }

    private PhilosopherState state = PhilosopherState.thinking;

    public Philosopher(String id, Chopstick left, Chopstick right, Table t){
        this.id = id;
        this.left = left;
        this.right = right;
        this.t = t;
    }

    public void printAction(String action){
        System.out.printf("Phil %s, %s\n", this.getID(), action);
    }

    public String getID(){
        return this.id;
    }

    public void run(){
        while(true){
            printAction(actions[0]);
            try{
                Thread.sleep(ThreadLocalRandom.current().nextInt(100, 200));
            }catch(InterruptedException ie){ie.printStackTrace();}
            System.out.printf("Phil %s tries to get left chopstick: %s\n", this.getID(), left.getID());
            if(t.getNumChopsticks() > 1){
                // se non è l'ultimo, puoi prenderlo
                System.out.printf("Phil %s got left chopstick: %s\n", this.getID(), left.getID());
                left.take();
                System.out.printf("Phil %s tries to get right chopstick: %s\n", this.getID(), left.getID());
                while(right.getState() == Chopstick.ChopstickState.busy){
                    try{
                        System.out.printf("Phil %s sleeping", this.getID());
                        Thread.sleep(ThreadLocalRandom.current().nextInt(100, 200));
                    }catch(InterruptedException ie){ie.printStackTrace();}
                }
                //se non è l'ultimo || se è l'ultimo e ne hai gia uno prendilo pure
                if(t.getNumChopsticks() > 1 || t.getNumChopsticks() == 1){
                    right.take();
                    System.out.printf("Phil %s got the right chopstick: %s\n", this.getID(), left.getID());
                }

                //eat
                System.out.printf("Phil %s is eating\n", this.getID());
                try{Thread.sleep(ThreadLocalRandom.current().nextInt(100, 200));
                } catch(InterruptedException ie1){ie1.printStackTrace();}
                System.out.printf("Phil %s is done eating\n", this.getID());
                right.leave();
                left.leave();
                System.out.printf("Phil %s has left both chopsticks\n", this.getID());
            }
            else {
                System.out.printf("Phil %s goes to sleep\n", this.getID());
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(100, 200));
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
    }

}
