package es1;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;

public class Main {

    public static void main(String[] args){

        try{
            Registry reg = LocateRegistry.getRegistry(1099);
            MCD stub = (MCD) reg.lookup("MCD");
            int x = 18; int y = 3;
            String response = String.valueOf(stub.mcd(x, y));
            System.out.println("response: "+response);
        }catch(Exception e){
            System.err.println("Client exception: "+e.toString());
            e.printStackTrace();
        }
        /**
        MCDimpl euclMCD = new MCDimpl();
        int x = 18;
        int y = 3;
        System.out.println("MCD("+x+","+y+")="+euclMCD.mcd(x,y));
        x = 18; y = 6;
        System.out.println("MCD("+x+","+y+")="+euclMCD.mcd(x,y));
         **/
    }
}
