import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote {
    public String toUpperCase(String x)      throws RemoteException;
    public void addListener(RemoteListener l) throws RemoteException;
    public void removeListener(RemoteListener l) throws RemoteException;
}


