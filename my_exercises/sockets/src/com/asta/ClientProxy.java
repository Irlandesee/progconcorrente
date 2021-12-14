package com.asta;

import java.io.IOException;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.util.LinkedList;

public class ClientProxy implements ServerInterface{

    private Socket s;

    public ClientProxy(Socket s){
        this.s = s;
    }

    public void quit() throws IOException{
        PrintWriter outStream = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
        outStream.println("QUIT");
        outStream.close();
    }

    /**
     * Ritorna il valore corrente offerto per il bene messo all'asta
     * @return
     */
    public double read() throws IOException{
        BufferedReader inStream = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter outStream = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
        String s = "";
        double biggestOffer = -1;

        outStream.println("READ");
        LinkedList<String> offers = new LinkedList<String>();
        while(!(s = inStream.readLine()).equals("DONE")){
            offers.add(s);
        }
        inStream.close();
        outStream.close();

        for(String offer : offers){
            System.out.println(offer);
            double tmp = Double.parseDouble(offer.split(" ")[1]);
            if(tmp > biggestOffer)
                biggestOffer = tmp;
        }
        return biggestOffer;
    }

    public void offer(double offer, Client client) throws IOException{
        PrintWriter outStream = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);

        String s = client.getID() + Double.toString(offer);

        outStream.println("OFFER");
        outStream.println(s);

        outStream.close();
    }

    /**
     * Ritorna il l'item corrente all'asta
     * @return
     */
    public String getItem() throws IOException{
        BufferedReader inStream = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter outStream = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);

        outStream.println("READ");
        String item = inStream.readLine();

        inStream.close();
        outStream.close();

        return item;
    }

}
