package es1;


import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MCDimpl extends UnicastRemoteObject implements MCD{

    public MCDimpl() throws RemoteException{
        super();
    }

    public int mcd(int n, int m) throws RemoteException{
        int r = 0;
        while(m != 0){
            r = n % m;
            n = m;
            m = r;
        }
        return r;
    }

    public static void main(String[] args){
        try{
            MCDimpl obj = new MCDimpl();
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("MCD", obj);
            System.err.println("Server ready");
        }catch(Exception e){
            System.out.println("Server exception: "+e.toString());
            e.printStackTrace();
        }
    }
}
