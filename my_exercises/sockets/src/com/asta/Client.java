package com.asta;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client{

    private static int id;
    private static final int PORT = 8080;
    private ClientProxy cp;

    public Client(int id){
        this.id = id;
    }

    public int getID(){
        return id;
    }

    private static String readUserCommand(){
        Scanner sc = new Scanner(System.in);
        System.out.println("READ, OFFER, GET_ITEM, QUIT");
        System.out.println("Insert command: ");

        String in = "";
        while(!((in = sc.nextLine()) != null)){
            System.out.println("Insert command: ");
            switch (in){
                case "READ":
                    sc.close();
                    return "READ";
                case "OFFER":
                    sc.close();
                    return "OFFER";
                case "GET_ITEM":
                    sc.close();
                    return "GET_ITEM";
                case "QUIT":
                    sc.close();
                    return "QUIT";
                default:
                    System.out.println("command undefined");
            }
        }
        sc.close();
        return "";
    }

    private static double readUserOffer(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your offer: ");
        double offer = sc.nextDouble();
        sc.close();
        return offer;
    }

    public static void main(String[] args){
        try{
            Client client = new Client(id++);
            Socket s = new Socket(InetAddress.getLocalHost(), 8080);
            client.cp = new ClientProxy(s);

            System.out.printf("Connected to: %s@%d",InetAddress.getLocalHost(), PORT);
            String userInput = "";
            while(!(userInput = readUserCommand()).equals("QUIT")){
                switch (userInput){
                    case "READ":
                        double highestOffer = 0;
                        try{
                            highestOffer = client.cp.read();
                            System.out.println("highest offer: "+highestOffer);
                        }catch(IOException io){
                            io.printStackTrace();
                        }
                        break;
                    case "OFFER":
                        double offer = readUserOffer();
                        try{
                            client.cp.offer(offer, client);
                        }catch(IOException io){
                            io.printStackTrace();
                        }
                        System.out.println("Offer sent");
                        break;
                    case "GET_ITEM":
                        String item = "";
                        try{
                            item = client.cp.getItem();
                        }catch(IOException io){
                            io.printStackTrace();
                        }
                        System.out.println("Item in offer: "+item);
                        break;
                }
            }
            //quit
            System.out.println("Quitting.");
            try{
                client.cp.quit();
                s.close();
            }catch(IOException io){
                io.printStackTrace();
            }
        }catch(IOException io){
            io.printStackTrace();
        }

    }



}
