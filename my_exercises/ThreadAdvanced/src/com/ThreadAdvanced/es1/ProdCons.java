package com.ThreadAdvanced.es1;

public class ProdCons {

    private int[] buffer;
    private int bufferLength;
    private int first, last; //first -> indice del elemento inserito più vecchio
                             //last -> indice dell'elemento inserito più recente
    private int numItems = 0;

    public ProdCons(int bufferLength){
        this.bufferLength = bufferLength;
        buffer = new int[bufferLength];
        first = 0;
        last = 0;
    }

    public int getCurrentSize(){
        return numItems;
    }

    /**
     * Aggiunge il parametro nella prima posizione libera disponibile
     * @param num
     * @return true se l'item è stato aggiunto
     */
    public synchronized void addToBuffer(int num){
        while(numItems == bufferLength){
            try{wait();}catch(InterruptedException ie){}
        }
        buffer[last] = num;
        last = (last+1)%bufferLength;
        numItems++;
        System.out.print(num+" written\n");
        notify();
    }

    /**
     * Rimuove il primo item disponibile alla rimozione, al suo indice inserisce -1
     * @return
     */
    public synchronized int removeFromBuffer(){
        int tmp;
        while(numItems == 0){
            try{
                wait();
            }catch(InterruptedException ie){}
        }
        numItems--;
        tmp = buffer[first];
        first = (first+1)%bufferLength;
        System.out.print(tmp+" read\n");
        notify();
        return tmp;
    }

}
