package com.ThreadAdvanced.es4;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CellaCondivisa {

    private BlockingQueue<Integer> queue;

    public CellaCondivisa(){
        queue = new LinkedBlockingQueue<Integer>();
    }

    public synchronized void insert(int num){
        System.out.printf("CellaCondivisa inserisce %d\n", num);
        queue.add(num);
    }

    public synchronized int remove(){
        int num = queue.poll();
        System.out.printf("CellaCondivisa rimuove %d\n", num);
        return num;
    }

    public synchronized boolean isEmpty(){
        return queue.isEmpty();
    }

    public synchronized  void printCurrentQueue(){
        int index = 0;
        int num = 0;
        System.out.println("CellaCondivisa printing");
        while(!queue.isEmpty()){
            num = queue.poll();
            if(index == queue.size())
                System.out.printf("%d\n", num);
            else
                System.out.printf("%d, ", num);
        }
        System.out.println("Done");
    }

}
