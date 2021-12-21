package es1;
import java.rmi.RemoteException;
import java.rmi.Remote;
public interface MCD extends Remote{
    public int mcd(int n, int m) throws RemoteException;
}
