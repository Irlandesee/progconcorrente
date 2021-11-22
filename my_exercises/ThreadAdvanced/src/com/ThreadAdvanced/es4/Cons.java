package com.ThreadAdvanced.es4;

import java.util.concurrent.ThreadLocalRandom;

public class Cons extends Thread{

    private int consumerID;
    private CellaCondivisa c;

    public Cons(CellaCondivisa c, int consumerID){
        this.c = c;
        this.consumerID = consumerID;
    }

    public void run(){

        System.out.printf("Cons %d starting\n", consumerID);
        while(c.isEmpty()){
            System.out.println("Queue is empty");
            System.out.printf("Consumer %d going to sleep\n", consumerID);
            try{
                this.sleep(ThreadLocalRandom.current().nextInt(200,500));
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("Queue is no longer empty");
        int num = 0;
        while(!c.isEmpty()){
            num = c.remove();
            System.out.printf("Consumer %d removes %d\n", consumerID, num);
        }

        System.out.printf("Consumer %d done\n", consumerID);

    }

}
