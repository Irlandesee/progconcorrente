package es2.client;

import es2.offer.Offer;
import es2.serverinterface.ServerInterface;

import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.LinkedList;

public class Client {

    private static int proxyID = 0;


    private static String readUserInput(){
        Scanner sc = new Scanner(System.in);
        String command = "";
        printHelp();
        System.out.print("inserisci comando > ");
        command = sc.nextLine();
        sc.close();
        return command;
    }

    private static Offer readOffer(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Inserisci $ > ");
        double amountOffered = sc.nextDouble();
        Offer ris = new Offer(String.valueOf(proxyID), "item0", amountOffered);
        sc.close();
        return ris;
    }

    public static void main(String[] args){
        Socket sock = null;
        LinkedList<Offer> offers = new LinkedList<Offer>();
        try{
            sock = new Socket(InetAddress.getLocalHost(), ServerInterface.PORT);
            ClientProxy cp = new ClientProxy(proxyID, sock);
            String userCommand = "";

            while(!(userCommand = readUserInput()).equals(ServerInterface.QUIT)){
                try{
                    switch(userCommand){
                        case ServerInterface.READ -> {
                            double offeredAmount = cp.read();
                            System.out.println("Top offeredAmount: "+offeredAmount);
                        }
                        case ServerInterface.OFFER -> {
                            cp.offer(readOffer());
                        }
                        case ServerInterface.GET_ITEM -> {
                            cp.getItem();
                        }
                        case ServerInterface.GET_OFFERS -> {
                            offers = (LinkedList<Offer>) cp.getOffers();
                        }
                    }
                }catch(ClassNotFoundException ce){
                    ce.printStackTrace();
                }
            }
            //quit
            System.out.println("Quitting");
            cp.quit();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }finally {
            try{
                sock.close();
            }catch(IOException ioe2){ioe2.printStackTrace();}
        }
    }

    private static void printHelp(){
        System.out.println("QUIT");
        System.out.println("READ");
        System.out.println("OFFER");
        System.out.println("GET_ITEM");
        System.out.println("GET_OFFERS");
    }

}
