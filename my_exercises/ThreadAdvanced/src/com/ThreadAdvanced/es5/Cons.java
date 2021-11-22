package com.ThreadAdvanced.es5;

import java.util.concurrent.ThreadLocalRandom;

public class Cons extends Thread{

    private int consumerID;
    private CellaCondivisa cc;
    private int numbersLeftToEat;

    public Cons(int consumerID, CellaCondivisa cc, int numbersLeftToEat){
        this.consumerID = consumerID;
        this.cc = cc;
        this.numbersLeftToEat = numbersLeftToEat;
    }

    private int nextInt(int origin, int upperLimit){
        return ThreadLocalRandom.current().nextInt(origin, upperLimit);
    }

    public void run(){
        if(getNumbersLeftToEat() == 0){
            this.numbersLeftToEat = nextInt(10, 50);
            System.out.printf("Consumer %d: numbers left to eat %d\n", consumerID, numbersLeftToEat);
        }
        else{
            while(getNumbersLeftToEat() > 0){
                System.out.printf("Consumer %d asks cc to return the head of the queue.\n", consumerID);
                Integer item = cc.remove();
                int num = 0;
                if (item != null){
                    num = item.intValue();
                    setNumbersLeftToEat();
                    System.out.printf("Consumer %d has eaten: %d\n", consumerID, num);
                    System.out.printf("Consumer %d has %d numbers left to eat", consumerID, getNumbersLeftToEat());
                }
                else
                    System.out.printf("Consumer %d: head was null!\n", consumerID);
            }
        }
    }

    public int getConsumerID(){
        return this.consumerID;
    }

    public void setConsumerID(int consumerID){
        this.consumerID = consumerID;
    }

    public int getNumbersLeftToEat(){
        return this.numbersLeftToEat;
    }

    public void setNumbersLeftToEat(){
        this.numbersLeftToEat--;
    }

}
