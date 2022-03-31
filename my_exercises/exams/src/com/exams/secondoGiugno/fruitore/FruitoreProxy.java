package com.exams.secondoGiugno.fruitore;

import com.exams.secondoGiugno.notizia.Notizia;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.IOException;
import java.util.LinkedList;

public class FruitoreProxy implements PubInterface{

    private static int proxyID = 0;
    private Socket sock;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;

    public FruitoreProxy(Socket sock){
        this.sock = sock;
        try{
            outStream = new ObjectOutputStream(sock.getOutputStream());
            inStream = new ObjectInputStream(sock.getInputStream());
        }catch(IOException io){
            System.out.printf("Errore nella creazione degli stream");
            io.printStackTrace();
        }
        proxyID++;
    }

    public void addTipo(String tipo) throws IOException {
        outStream.writeObject(PubInterface.ADD);
        outStream.writeObject(tipo);
        outStream.flush();
        try{
            String response = inStream.readObject().toString();
            if(response.equals(PubInterface.OK))
                System.out.printf("Proxy %d ha aggiunto correttamente il tipo notizia desiderato\n", proxyID);
            else
                System.out.printf("Proxy %d, Errore nell'aggiunta del tipo notizia\n", proxyID);
        }catch(ClassNotFoundException ce){ce.printStackTrace();}
    }

    public void removeTipo(String tipo) throws IOException{
        outStream.writeObject(PubInterface.REMOVE);
        outStream.writeObject(tipo);
        outStream.flush();
        try{
            String response = inStream.readObject().toString();
            if(response.equals(PubInterface.OK))
                System.out.printf("Proxy %d ha rimosso correttamente il tipo notizia dediderato\n", proxyID);
            else
                System.out.printf("Proxy %d errore nella rimozio del tipo notizia\n", proxyID);
        }catch(ClassNotFoundException ce){
            ce.printStackTrace();
        }
    }

    public LinkedList<Notizia> listen() throws IOException{
        outStream.writeObject(PubInterface.LISTEN);
        String response;
        LinkedList<Notizia> notizie = new LinkedList<Notizia>();
        try{
            if((response = inStream.readObject().toString()).equals(PubInterface.OK)){
                System.out.printf("Proxy %d riceve notizie\n", proxyID);
                Notizia ricevuta;
                while((ricevuta = (Notizia) inStream.readObject()) != null){
                    System.out.printf("proxy %d > %s\n", proxyID, ricevuta);
                    notizie.add(ricevuta);
                }
            }
            System.out.printf("Proxy %d tutte le notizie sottoscritte ricevute\n", proxyID);
        }catch(ClassNotFoundException ce){ce.printStackTrace();}
        return notizie;
    }

    public void quit() throws IOException{
        outStream.writeObject(PubInterface.QUIT);
        outStream.flush();
    }
}
