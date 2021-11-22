package com.filosofi.es3;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
public class Handler {

    private Philosopher[] philosophers;
    private Chopstick[] chopsticks;

    private PriorityBlockingQueue<String> requestQueue;
    private LinkedBlockingQueue<Philosopher> philosophersRunning;

    public Handler(Philosopher[] philosophers, Chopstick[] chopsticks){
        this.philosophers = philosophers;
        this.chopsticks = chopsticks;

        requestQueue = new PriorityBlockingQueue<String>();
        philosophersRunning = new LinkedBlockingQueue<Philosopher>();
    }

    public synchronized void requestTwo(String request){
        if(philosophersRunning.isEmpty()){
            //non ce nessuno in esecuzione
        }
        else{
            //Controllo quanti sono in esecuzione
            if(philosophersRunning.size() > 2) //non ce posto
                requestQueue.add(request);
            else{ //un solo fil in esecuzione
                Philosopher philRunning = philosophersRunning.peek();
                int leftChopPhilRunning = philRunning.getLeftChopstickID();
                int rightChopPhilRunning = philRunning.getRightChopstickID();

            }
        }
        /**
        while(!(l.isAvailable()) && !(r.isAvailable())){
            try{wait();}catch(InterruptedException ie){ie.printStackTrace();}
        }**/
    }

    public synchronized void leaveTwo(Chopstick l, Chopstick r){

    }

}
