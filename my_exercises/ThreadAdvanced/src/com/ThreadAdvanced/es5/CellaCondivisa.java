package com.ThreadAdvanced.es5;

import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CellaCondivisa{

    private int cellaCondivisaID;
    private int queueCapacity;
    private BlockingQueue<Integer> queue;

    public CellaCondivisa(int cellaCondivisaID, int queueCapacity){
        this.cellaCondivisaID = cellaCondivisaID;
        this.queueCapacity = queueCapacity;
        queue = new LinkedBlockingQueue<Integer>(queueCapacity);
    }

    public synchronized int getCurrentSize(){
        int currentSize = queue.size();
        System.out.printf("CC %d current size: %d\n", cellaCondivisaID, currentSize);
        return currentSize;
    }

    public synchronized void printCurrentSize(){
        System.out.printf("CC %d current size: %d\n", cellaCondivisaID, queue.size());
    }

    public synchronized boolean insert(int number){
        System.out.printf("CC %d inserisce %d\n", cellaCondivisaID, number);
        if(getCurrentSize() == queueCapacity){
            try{
                wait();
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
        boolean success = queue.offer(number);
        if(!success){
            System.out.printf("CC %d inserimento fallito, %d\n", cellaCondivisaID, number);
            return false;
        }
        System.out.printf("CC %d inserimento riuscito, %d\n", cellaCondivisaID, number);
        this.printCurrentSize();
        notify();
        return true;
    }

    public synchronized Integer remove(){
        System.out.printf("CC %d rimuove la testa della coda\n", cellaCondivisaID);
        if(getCurrentSize() == 0){
            try{
                wait();
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
        }
        Integer head = queue.poll();
        notify();
        if(head != null){
            System.out.printf("CC %d ritorna Integer %d\n", cellaCondivisaID, head.intValue());
            return head;
        }
        else
            System.out.printf("CC %d ritorna null!\n", cellaCondivisaID);
        return null;
    }

    public synchronized void printCurrentQueue(){
        System.out.println("======================================");
        System.out.printf("CC %d printing current queue!\n", cellaCondivisaID);
        Iterator<Integer> it = queue.iterator();
        int i = 0;
        while(it.hasNext()){
            System.out.printf("pos [%d] : [%d]\n", i, it.next().intValue());
            i++;
        }
        System.out.printf("CC %d done printing\n", cellaCondivisaID);
        System.out.println("======================================");
    }

    public int getCellaCondivisaID(){
        return this.cellaCondivisaID;
    }

    public void setCellaCondivisaID(int cellaCondivisaID){
        this.cellaCondivisaID = cellaCondivisaID;
    }

    public int getQueueCapacity(){
        return this.queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity){
        this.queueCapacity = queueCapacity;
    }

}
