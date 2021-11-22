package com.ThreadAdvanced.es1;

import java.util.concurrent.ThreadLocalRandom;

public class Prod extends Thread{

    private static int producerID = 0;

    private ThreadLocalRandom randGen;
    private ProdCons prodCons;
    private int numberOfItemsToGenerate;

    public Prod(ProdCons prodCons, int numberOfItemsToGenerate){
        producerID++;
        this.prodCons = prodCons;
        this.numberOfItemsToGenerate = numberOfItemsToGenerate;
    }

    public void run(){
        int itemsGenerated = 0;
        while(itemsGenerated < numberOfItemsToGenerate){
            try{
                Thread.sleep(ThreadLocalRandom.current().nextInt(10, 100));
            }catch(InterruptedException ie){}
            int num = (int) (100*Math.random());
            System.out.printf("Producer %d aggiunge %d\n", producerID, num);
            prodCons.addToBuffer(num);
            itemsGenerated++;
        }
    }

}
