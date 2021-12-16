package com.socketAdvanced.es1.client;

import java.net.InetAddress;
import java.net.Socket;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Iterator;

import com.socketAdvanced.es1.BankInterface.BankInterface;
import com.socketAdvanced.es1.operations.OperationRequest;
import com.socketAdvanced.es1.operations.Result;

public class Client {


    private int myAccountNum;

    Client(int accNum){
        this.myAccountNum = accNum;
    }

    private String readOperation(){
        Scanner sc = new Scanner(System.in);
        String[] permittedOperations = {"quit", "deposit", "withdraw"};
        String operation = "";
        while(!(operation = sc.nextLine()).equals("")){
            for(String tmp : permittedOperations)
                if(operation.equals(tmp))
                    return operation;
            System.out.println("Operazione non valida");
        }
        sc.close();
        return operation;
    }

    private int readAmount(){
        Scanner sc = new Scanner(System.in);
        int amount = 0;
        while(((amount = sc.nextInt()) <= 0 || (amount > 1000)))
            System.out.println("Quantità non valida");
        sc.close();
        return amount;
    }

    public int getMyAccountNum(){return this.myAccountNum;}

    public static void main(String[] args){
        Socket sock = null;
        try{
            Client c = new Client(0);
            sock = new Socket(InetAddress.getLocalHost(), BankInterface.PORT);
            ClientProxy cp = new ClientProxy(c, sock);
            String op = "";

            while(!(op = c.readOperation()).equals(BankInterface.QUIT)){
                int amount = c.readAmount();
                Result r = cp.executeOperation(op, amount);
                System.out.println("From bank: \n"+r.toString());
            }
            //quit
            System.out.println("Quitting");
            cp.quit();
        }catch(IOException io){io.printStackTrace();}
        finally{

            System.out.println("Closing socket @"+BankInterface.PORT);
            try{
                sock.close();
            }catch(IOException io2){io2.printStackTrace();}
        }
    }

}
