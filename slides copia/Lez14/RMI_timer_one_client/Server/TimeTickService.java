import java.rmi.RemoteException;

public interface TimeTickService extends RemoteObservable {
    void setTick(int period) throws RemoteException;
}
