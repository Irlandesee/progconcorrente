package com.ThreadAdvanced.es5;

import java.util.concurrent.ThreadLocalRandom;

public class Prod extends Thread{

    private int producerID;
    private CellaCondivisa cc;
    private int numbersLeftToGenerate = 0;

    public Prod(int producerID, CellaCondivisa cc, int numbersLeftToGenerate){
        this.producerID = producerID;
        this.cc = cc;
        this.numbersLeftToGenerate = numbersLeftToGenerate;
    }

    private int nextInt(int origin, int upperLimit){
        return ThreadLocalRandom.current().nextInt(origin, upperLimit);
    }

    public void run(){
        if(getNumbersLeftToGenerate() == 0) {
            this.numbersLeftToGenerate = nextInt(10, 50);
            System.out.printf("Producer %d: numbers left to generate: %d\n", producerID, getNumbersLeftToGenerate());
        }
        else{
            while(getNumbersLeftToGenerate() > 0){
                int num = nextInt(0, 100);
                System.out.printf("Producer %d asks cc to insert %d\n", producerID, num);
                boolean success = cc.insert(num);

                if(success)
                    System.out.printf("Producer %d has inserted successfully %d\n", producerID, num);
                else
                    System.out.printf("Producer %d has failed to insert %d\n", producerID, num);

                setNumbersLeftToGenerate();
                System.out.printf("Producer %d: numbers left to generate: %d\n", producerID, getNumbersLeftToGenerate());
            }
        }
    }

    public int getProducerID(){
        return this.producerID;
    }

    public void setProducerID(int producerID){
        this.producerID = producerID;
    }

    public int getNumbersLeftToGenerate(){
        return this.numbersLeftToGenerate;
    }

    public void setNumbersLeftToGenerate(){
        this.numbersLeftToGenerate--;
    }


}
