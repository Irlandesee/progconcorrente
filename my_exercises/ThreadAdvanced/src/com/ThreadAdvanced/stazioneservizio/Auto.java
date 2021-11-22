package com.ThreadAdvanced.stazioneservizio;

import java.util.concurrent.ThreadLocalRandom;

public class Auto extends Thread{

    private int id;
    private Pompa p;

    private int carburanteMax;
    private int carburanteCorrente;

    private static final String[] carburanti = {"benzina", "gasolio", "gpl"};
    private String carburante;

    public Auto(int id, Pompa p){
        this.id = id;
        this.p = p;
        init();
    }

    private void init(){
        this.carburanteMax = ThreadLocalRandom.current().nextInt(50, 100);
        this.carburanteCorrente = ThreadLocalRandom.current().nextInt(10, 90);
        this.carburante = carburanti[ThreadLocalRandom.current().nextInt(0, 3)];

    }


    public int getID(){
        return this.id;
    }

    public void setID(int id){
        this.id = id;
    }

    public int getCarburanteMax(){
        return this.carburanteMax;
    }

    public int getCarburanteCorrente(){
        return this.carburanteCorrente;
    }

    public String getCarburante(){
        return this.carburante;
    }

    public void run(){

    }

    public String toString(){
        return "Auto: "+this.getID()+"\nCarburante Max:"+this.getCarburanteMax()+
                "\nCarburante Corrente:"+this.getCarburanteCorrente()+
                "\nDa rifornire:"+(this.getCarburanteMax()-this.getCarburanteCorrente());
    }

}
