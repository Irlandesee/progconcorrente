package com.filosofi.es3;

public class Philosopher extends Thread{

    private int id;
    private Chopstick left;
    private Chopstick right;

    public enum PhilosopherState {
        thinking, //waiting
        hungry, //ready
        eating //running
    }

    private PhilosopherState state = PhilosopherState.thinking;

    public Philosopher(int id, Chopstick left, Chopstick right){
        this.id = id;
        this.left = left;
        this.right = right;
    }

    public void run(){

    }

    public int getPhilID(){
        return this.id;
    }

    public void setPhilID(int id){
        this.id = id;
    }

    public int getLeftChopstickID(){
        return this.left.getChopstickID();
    }

    public int getRightChopstickID(){
        return this.right.getChopstickID();
    }

}
