package com.ThreadAdvanced;

import com.ThreadAdvanced.es1.*;
import com.ThreadAdvanced.es4.*;
import com.ThreadAdvanced.es5.*;
import com.ThreadAdvanced.es5.CellaCondivisa;
import com.ThreadAdvanced.es5.Prod;
import com.ThreadAdvanced.es5.Cons;

public class Main {

    public static void main(String[] args){
        /** es1
        ProdCons cellaCondivisa = new ProdCons(10);
        Prod p1 = new Prod(cellaCondivisa, 20);
        Prod p2 = new Prod(cellaCondivisa, 20);
        Cons c = new Cons(cellaCondivisa, 40);

        c.start();
        p1.start();
        p2.start();


        try{
            p1.join();
            p2.join();
            c.join();
        }catch(InterruptedException ie){}
         **/

        /** es4

        CellaCondivisa cc = new CellaCondivisa();
        Prod p1 = new Prod(cc, 1, 10);
        Prod p2 = new Prod(cc, 2, 10);
        Cons c = new Cons(cc, 1);
        p1.start();
        p2.start();



        c.start();
        try{

            p1.join();
            p2.join();
            c.join();
        }catch(InterruptedException e1){
            e1.printStackTrace();
        }

        //c.printCurrentQueue();

         **/

        /** es5
        CellaCondivisa cc = new CellaCondivisa(0, 10);
        Prod p = new Prod(1, cc, 20);
        Cons c = new Cons(2, cc, 21);

        p.start();
        c.start();

        try{
            p.join();
            c.join();
            System.out.println("Prod and Cons are done");
        }catch(InterruptedException e){e.printStackTrace();}
        System.out.println("Main done");
         **/

        /** stazione servizio **/

    }
}
