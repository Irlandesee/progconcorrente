package com.ThreadAdvanced.stazioneservizio;


public class Pompa {

    private enum OccupiedState{
        inoperosa,
        pronta,
        inselezione,
        erogazione,
        erogazioneCompleta
    }

    private static final int CARBURANTEMAX = 10000;
    private int id;
    private int carburanteCorrente = 10000;
    private int numeroAutoServite = 0;

    private boolean status;
    private OccupiedState state = OccupiedState.inoperosa;

    public Pompa(int id){
        this.id = id;
        status = true; //true for pompa libera
    }

    public int getCarburanteCorrente(){
        return this.carburanteCorrente;
    }

    public boolean getStatus(){
        return this.status;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public synchronized void occupy(Auto a){
        while(this.status == false){
            try{
                wait();
            }catch(InterruptedException ie){}
        }
        this.status = false;
        state = OccupiedState.pronta;
        System.out.printf("Pompa %d,%s  occupata da auto: %s", this.id, this.state,  a.toString());
        notify();
    }

    public synchronized void selezioneCarburante(Auto a){
        while(this.status == false){
            try{wait();}catch(InterruptedException ie){}
        }
        String carburante = a.getCarburante();
        state = OccupiedState.inselezione;
        System.out.printf("Pompa %d,%s in  %s", this.id, this.state,  carburante);
    }



}
