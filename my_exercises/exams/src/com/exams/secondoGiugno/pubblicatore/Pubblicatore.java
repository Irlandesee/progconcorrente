package com.exams.secondoGiugno.pubblicatore;

import com.exams.secondoGiugno.notizia.Notizia;
import com.exams.secondoGiugno.produttore.Produttore;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Iterator;

public class Pubblicatore extends Thread{

    private LinkedList<Produttore> listaProduttori;
    private ConcurrentHashMap<Integer, PubblicatoreSchiavo> slaveList;

    private LinkedBlockingQueue<Notizia> notizie;

    private ServerSocket ss;

    private final int PORT = 9999;
    private int clientsConnected;

    public Pubblicatore(){
        listaProduttori = new LinkedList<Produttore>();
        slaveList = new ConcurrentHashMap<Integer, PubblicatoreSchiavo>();
        notizie = new LinkedBlockingQueue<Notizia>();
        try{
            ss = new ServerSocket(PORT);
        }catch(IOException io){io.printStackTrace();}

        for(int i = 0; i < 4; i++){
            Produttore pr = new Produttore(this);
            listaProduttori.add(pr);
        }

    }

    public synchronized LinkedList<Notizia> getNotizie(){
        LinkedList<Notizia> daPubblicare = new LinkedList<Notizia>();
        for(Notizia n : notizie)
            daPubblicare.add(n);
        return daPubblicare;
    }

    public synchronized void addNotizia(Notizia n){
        notizie.add(n);
    }

    public void run(){

        Socket s;
        int i = 0;
        try{
            while((s = ss.accept()) != null){
                PubblicatoreSchiavo schiavo = new PubblicatoreSchiavo(s, this);
                slaveList.put(i, schiavo);
                i++;
                clientsConnected++;
            }
        }catch(IOException io){io.printStackTrace();}

    }
}
