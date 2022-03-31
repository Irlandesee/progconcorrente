package com.socketAdvanced.es1.client;

import java.net.InetAddress;
import java.net.Socket;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

import com.socketAdvanced.es1.BankInterface.BankInterface;
import com.socketAdvanced.es1.operations.OperationRequest;
import com.socketAdvanced.es1.operations.Result;

public class Client {


    private int myAccountNum;

    Client(int accNum){
        this.myAccountNum = accNum;
    }

    private String readOperation(Scanner sc){
        String[] permittedOperations = {"quit", "deposit", "withdraw"};
        String operation = "";
        System.err.print("Inserisci operazione >");
        while(!(operation = sc.nextLine()).equals("")){
            for(String s : permittedOperations)
                if(operation.equalsIgnoreCase(s)) {
                    return operation;
                }
            System.err.println("Operazione non valida");
            System.err.print("Inserisci operazione >");
        }
        return operation;
    }

    private int readAmount(Scanner sc){
        int amount = 0;
        System.err.print("Inserisci quantità > ");
        while(((amount = sc.nextInt()) <= 0 || (amount > 1000)))
            System.out.println("Quantità non valida");
        return amount;
    }

    public int getMyAccountNum(){return this.myAccountNum;}

    public static void main(String[] args){
        Socket sock = null;
        Scanner sc = new Scanner(System.in);
        Result r = null;
        try{
            Client c = new Client(0);
            sock = new Socket(InetAddress.getLocalHost(), BankInterface.PORT);
            ClientProxy cp = new ClientProxy(c, sock);
            String op = "";

            while(!(op = c.readOperation(sc)).equalsIgnoreCase(BankInterface.QUIT)){
                int amount = c.readAmount(sc);
                cp.executeOperation(op, amount);
                System.out.println("From bank: \n"+cp.getResult().toString());
            }
            //quit
            System.out.println("Quitting");
            cp.quit();
        }catch(IOException io){io.printStackTrace();}
        /**
        finally{

            System.out.println("Closing socket @"+BankInterface.PORT);
            System.out.println("Closing scanner");
            try{
                sock.close();
                sc.close();
            }catch(IOException io2){io2.printStackTrace();}
        }
         **/
    }

}
