package com.ThreadAdvanced.es4;

import java.util.concurrent.ThreadLocalRandom;


public class Prod extends Thread{

    private int producerID;
    private int numInteriDaGenerare;
    private CellaCondivisa c;

    public Prod(CellaCondivisa c, int producerID, int numInteriDaGenerare){
        this.producerID = producerID;
        this.numInteriDaGenerare = numInteriDaGenerare;
        this.c = c;
    }

    public void run(){
        int num = 0;
        for(int i = 0; i < this.numInteriDaGenerare; i++){
            num = ThreadLocalRandom.current().nextInt(100);
            System.out.printf("Thread producer %d crea %d\n", producerID, num);
            c.insert(num);
        }

        System.out.printf("Producer %d done\n", producerID);

    }

}
