
import java.rmi.Remote;
import java.rmi.RemoteException;

interface RemoteListener extends Remote {
    public void remoteEvent(Object param) throws RemoteException;
}
