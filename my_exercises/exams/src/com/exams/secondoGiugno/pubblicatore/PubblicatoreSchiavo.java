package com.exams.secondoGiugno.pubblicatore;

import com.exams.secondoGiugno.notizia.Notizia;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;

public class PubblicatoreSchiavo extends Thread{

    private static int schiavoID = 0;
    private Pubblicatore pub;
    private Socket sock;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    private boolean runCondition;

    private LinkedList<String> tipiNotiziaDaPubblicare;

    public PubblicatoreSchiavo(Socket sock, Pubblicatore pub){
        this.sock = sock;
        this.pub = pub;
        schiavoID++;
        tipiNotiziaDaPubblicare = new LinkedList<String>();
        try{
            outStream = new ObjectOutputStream(sock.getOutputStream());
            inStream = new ObjectInputStream(sock.getInputStream());
        }catch(IOException io){io.printStackTrace();}
        runCondition = true;
        this.start();
    }

    public void run(){
        while(runCondition && !(sock.isClosed())){
            try{
                String req = inStream.readObject().toString();
                switch(req){
                    case "ADD":
                        String tipoNotiziaDaAggiungere = inStream.readObject().toString();
                        tipiNotiziaDaPubblicare.add(tipoNotiziaDaAggiungere);
                        outStream.writeObject("OK");
                        outStream.flush();
                        break;
                    case "REMOVE":
                        String tipoNotiziaDaRimuovere = inStream.readObject().toString();
                        if(tipiNotiziaDaPubblicare.contains(tipoNotiziaDaRimuovere)){
                            tipiNotiziaDaPubblicare.remove(tipoNotiziaDaRimuovere);
                            outStream.writeObject("OK");
                        }
                        else
                            outStream.writeObject("KO");
                        outStream.flush();
                        break;
                    case "LISTEN":
                        LinkedList<Notizia> daPubblicare = pub.getNotizie();
                        Iterator<Notizia> it = daPubblicare.iterator();
                        outStream.writeObject("OK");
                        System.out.printf("pub schiavo %d scrive su stream\n", schiavoID);
                        while(it.hasNext()){
                            Notizia n = (Notizia) it.next();
                            if(tipiNotiziaDaPubblicare.contains(n.getTipo())){
                                System.out.printf("Schiavo %d pubblica %s\n", schiavoID, n);
                                outStream.writeObject(n);
                            }
                        }
                        outStream.flush();
                        break;
                    case "QUIT":
                        System.out.println("Client si disconnette");
                        runCondition = false;
                        break;
                }
            }catch(ClassNotFoundException | IOException e){e.printStackTrace();}
        }
    }



}
