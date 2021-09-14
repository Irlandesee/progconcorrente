package client;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.net.UnknownHostException;

import tavola.Tavola;

public class Client {

    private void exec(String addr){
        InetAddress inetAddress;
        try{
            inetAddress = InetAddress.getByName(addr);
        }catch(UnknownHostException e){
            System.out.println("Errore nella creazione dell indirizzo.");
            e.printStackTrace();
            System.exit(1);
        }
        //creo slave

    }

    public static void main(String[] args){

    }



}
