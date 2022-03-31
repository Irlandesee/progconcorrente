package com.socketAdvanced.es1.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import com.socketAdvanced.es1.operations.Result;

public class ClientSlave extends Thread{

    private ObjectInputStream in;
    private Result res;

    public ClientSlave(ObjectInputStream in, Result res){
        this.in = in;
        this.res = res;
        start();
    }

    public void run(){
        System.err.println("Listener started");
        try{
            Result r = (Result) in.readObject();
            System.out.println("Read object from server");
            res.setComplete(r);
            this.res = r;
        }catch(ClassNotFoundException | IOException e){e.printStackTrace();}
        finally{System.err.println("Listener joining");}
    }

    public Result getRes(){return this.res;}

}
