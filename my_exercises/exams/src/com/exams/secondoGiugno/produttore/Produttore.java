package com.exams.secondoGiugno.produttore;

import com.exams.secondoGiugno.notizia.Notizia;
import com.exams.secondoGiugno.pubblicatore.Pubblicatore;

import java.util.concurrent.ThreadLocalRandom;

public class Produttore extends Thread{

    private static int produttoreID;
    private final int sleeptime = 5000;

    private final String[] tipiNotizia = {"politica", "attualita", "scienza", "sport"};
    private String tipoProduttore;
    private Pubblicatore pub;

    private boolean runCondition;

    public Produttore(Pubblicatore pub){
        this.pub = pub;
        this.runCondition = true;
        produttoreID++;
        this.start();
    }

    public void run(){

        while(runCondition){
            this.tipoProduttore = tipiNotizia[ThreadLocalRandom.current().nextInt(0, tipiNotizia.length-1)];

            String notiziaDaPubblicare = "X:" + produttoreID;
            Notizia n = new Notizia(tipoProduttore, notiziaDaPubblicare);
            pub.addNotizia(n);
            try{
                Thread.sleep(sleeptime);
            }catch(InterruptedException ie){ie.printStackTrace();}
        }

    }

    public String getTipoProduttore(){return this.tipoProduttore;}
}
