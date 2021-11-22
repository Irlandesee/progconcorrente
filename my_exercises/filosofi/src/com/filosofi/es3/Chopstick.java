package com.filosofi.es3;

public class Chopstick {

    private int id;

    private enum ChopstickState {
        available,
        busy
    }

    private ChopstickState state = ChopstickState.available;

    public Chopstick(int id){
        this.id = id;
    }

    public boolean isAvailable(){
        return state == ChopstickState.available;
    }

    public void take(){
        state = ChopstickState.busy;
    }

    public void leave(){
        state = ChopstickState.available;
    }

    public int getChopstickID(){
        return this.id;
    }

    public void setChopstickID(int id){
        this.id = id;
    }

}
