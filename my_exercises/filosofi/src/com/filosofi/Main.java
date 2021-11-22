package com.filosofi;

import com.filosofi.es1.Chopstick;
import com.filosofi.es1.Philosopher;
import com.filosofi.es1.Table;

public class Main {

    public static void main(String[] args) {

        Philosopher[] philosophers = new Philosopher[5];
        Chopstick[] chopsticks = new Chopstick[5];
        Table t = new Table(philosophers, chopsticks);
        for(int i = 0; i < 5; i++){
            Chopstick left = new Chopstick(""+i);
            Chopstick right = new Chopstick(""+i+1);
            Philosopher p = new Philosopher(""+i, left, right, t);
            philosophers[i] = p;
            chopsticks[i] = left;
            if(i == 4)
                chopsticks[i] = right;
        }
        System.out.println("Launching phils");
        for(int i = 0; i < 5; i++)
            philosophers[i].start();

    }
}
