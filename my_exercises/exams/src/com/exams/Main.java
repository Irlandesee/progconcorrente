package com.exams;

import com.exams.onlinegame.server.Server;
import com.exams.onlinegame.client.Client;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random rand = new Random();

        System.out.println("Launching and starting server");
        Server serv = new Server();
        serv.start();


        Client c = new Client(rand.nextInt(11), 0);
        Client c1 = new Client(rand.nextInt(11), 1);
        c.start();
        c1.start();

        try{
            Thread.sleep(5000);
            c.join();
            c1.join();
        }catch(InterruptedException ie){ie.printStackTrace();}

        Client c2 = new Client(rand.nextInt(11), 2);
        Client c3 = new Client(rand.nextInt(11), 3);
        c2.start();

        try{
            Thread.sleep(300);
        }catch(InterruptedException ie){ie.printStackTrace();}

        c3.start();

        try{
            c2.join();
            c3.join();
        }catch(InterruptedException ie){ie.printStackTrace();}

        System.out.println("Main terminating");
    }
}
