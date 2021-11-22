package com.filosofi.es1;

public class Table {

    private Philosopher[] philosophers;
    private Chopstick[] chopsticks;

    private int numPhilospher;
    private int numChopsticks;

    public Table(Philosopher[] philosophers, Chopstick[] chopsticks){
        this.philosophers = philosophers;
        this.chopsticks = chopsticks;
        numPhilospher = philosophers.length;
        numChopsticks = chopsticks.length;
    }

    public int getNumPhilospher(){
        return this.numPhilospher;
    }

    public int getNumChopsticks(){
        return this.numChopsticks;
    }

}
