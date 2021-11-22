package com.filosofi.es1;

import java.util.concurrent.ThreadLocalRandom;

public class Chopstick{

    private String id;

    public enum ChopstickState {
        available,
        busy
    }

    private ChopstickState state = ChopstickState.available;

    public Chopstick(String id){
        this.id = id;
    }

    public synchronized void take(){
        while(this.state == ChopstickState.busy){
            try{
                wait(ThreadLocalRandom.current().nextInt(10, 30));
            }catch(InterruptedException ie){ie.printStackTrace();}
        }
        this.state = ChopstickState.busy;
        System.out.printf("Chopstick %s, is: %s\n", this.id, this.state);

    }

    public synchronized void leave(){
        this.state = ChopstickState.available;
        System.out.printf("Chopstick %s, is: %s\n", this.id, this.state);
        notifyAll();
    }

    public ChopstickState getState(){
        return this.state;
    }

    public String getID(){
        return this.id;
    }

}
