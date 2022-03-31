package com.exams.secondoGiugno.fruitore;

import com.exams.secondoGiugno.notizia.Notizia;

import java.net.InetAddress;
import java.net.Socket;
import java.io.IOException;
import java.util.LinkedList;

public class Fruitore extends Thread{

    private FruitoreProxy fproxy;
    private Socket sock;

    public Fruitore(){
        try{
            this.sock = new Socket(InetAddress.getLocalHost(), PubInterface.PORT);
        }catch(IOException io){io.printStackTrace();}
        this.fproxy = new FruitoreProxy(sock);
    }

    private void aggiungi(String tipo){
        System.out.println("Fruitore aggiunge tipo");
        try{
            fproxy.addTipo(tipo);
        }catch(IOException io){io.printStackTrace();}
    }

    private void rimuovi(String tipo){
        System.out.println("Fruitore rimuove tipo");
        try{
            fproxy.removeTipo(tipo);
        }catch(IOException io){io.printStackTrace();}
    }

    private LinkedList<Notizia> ascolta(){
        System.out.println("Fruitore si mette in ascolto");
        LinkedList<Notizia> notizie = new LinkedList<Notizia>();
        try{
            notizie = fproxy.listen();
            for(Notizia n : notizie)
                System.out.println(n);
        }catch(IOException io){io.printStackTrace();}

        return notizie;
    }

    private void disconnetti(){
        System.out.println("Fruitore si disconnette");
        try{fproxy.quit();}catch(IOException io){io.printStackTrace();}
    }

    public void run(){
        Fruitore fr = new Fruitore();
        int runtime = 5;
        fr.aggiungi("scienza");
        fr.aggiungi("sport");
        while(runtime > 0){
            LinkedList<Notizia> notizie = fr.ascolta();
            for(Notizia n : notizie)
                System.out.println("Fruitore > "+n);
            runtime--;
        }
        fr.disconnetti();
    }

}
