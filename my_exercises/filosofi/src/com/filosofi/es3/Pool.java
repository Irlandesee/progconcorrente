package com.filosofi.es3;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Pool{

    private Philosopher[] philosophers;
    private Chopstick[] chopsticks;

    private PriorityBlockingQueue<String> requestsQueue;
    private LinkedBlockingQueue<Philosopher> philRunning;

    public Pool(Philosopher[] phils, Chopstick[] chops){
        this.philosophers = phils;
        this.chopsticks = chops;

        requestsQueue = new PriorityBlockingQueue<String>();
        philRunning = new LinkedBlockingQueue<Philosopher>();
    }

    /**
     * Restituisce la request da più tempo in waiting
     * @return
     */
    private synchronized String pickWaitingPhilosopher() {
        if(!requestsQueue.isEmpty()){
            return requestsQueue.poll();
        }
        return "";
    }

    public synchronized void requestTwo(String request){
        if(philRunning.isEmpty()){
            String[] reqTmp = request.split(",");
            int leftRequestedChopstick = Integer.parseInt(reqTmp[0]);
            int rightRequestedChopstick = Integer.parseInt(reqTmp[1]);
            int pID = Integer.parseInt(reqTmp[3]);
            Philosopher p = philosophers[pID];
            Chopstick left = chopsticks[leftRequestedChopstick];
            Chopstick right = chopsticks[rightRequestedChopstick];
            left.take();
            right.take();
            p.setPhilState(Philosopher.PhilosopherState.eating);
            philRunning.add(p);
        }
        else{
            //non ce posto per l'esecuzione
            if(philRunning.size() > 2) {
                requestsQueue.add(request);
            }
            else{ //un solo fil in esecuzione

            }
        }
    }

    public synchronized void leaveTwo(){

    }

}
