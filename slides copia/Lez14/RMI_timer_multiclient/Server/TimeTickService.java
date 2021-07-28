import java.rmi.RemoteException;

public interface TimeTickService extends RemoteObservable {
    void setPeriod(RemoteObserver o, int period) throws RemoteException;
}
