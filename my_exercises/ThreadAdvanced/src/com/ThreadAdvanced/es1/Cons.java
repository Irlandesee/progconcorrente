package com.ThreadAdvanced.es1;

import java.util.concurrent.ThreadLocalRandom;

public class Cons extends Thread{

    private static int consumerID = 0;

    private ProdCons cellaCondivisa;
    private int numberOfItemsToRead;

    public Cons(ProdCons cellaCondivisa, int numberOfItemsToRead){
        this.cellaCondivisa = cellaCondivisa;
        this.numberOfItemsToRead = numberOfItemsToRead;
        consumerID++;
    }

    public void run(){
        int itemsRead = 0;
        int numberConsumed;
        while(itemsRead <= numberOfItemsToRead){
            numberConsumed = cellaCondivisa.removeFromBuffer();
            System.out.printf("Consumer %d mangia %d\n", consumerID, numberConsumed);
            itemsRead++;
        }
        try{
            Thread.sleep(ThreadLocalRandom.current().nextInt(10, 100));
        }catch(InterruptedException ie){}
    }

}
