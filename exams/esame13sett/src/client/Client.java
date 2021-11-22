package client;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.net.UnknownHostException;

import tavola.Tavola;

public class Client {

    private char segnaposto;
    private int id;
    private Tavola t;

    public Client(int _id, char _segnaposto){
        id = _id;
        segnaposto = _segnaposto;
        t = new Tavola();
    }

    public void exec(String addr){
        InetAddress inetAddress;

        try{
            inetAddress = InetAddress.getByName(addr);
            new ClientThread(inetAddress, id, t);
        }catch(UnknownHostException e){
            System.out.println("Errore nella creazione dell indirizzo.");
            e.printStackTrace();
            System.exit(1);
        }

    }

    public char getSegnaposto() {
        return segnaposto;
    }

    public void setSegnaposto(char segnaposto) {
        this.segnaposto = segnaposto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
