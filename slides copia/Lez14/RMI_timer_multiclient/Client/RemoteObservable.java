import java.rmi.*;

public interface RemoteObservable extends Remote {
	public void addRemoteObserver(RemoteObserver o) throws RemoteException;
	public void removeRemoteObserver(RemoteObserver o) throws RemoteException;
	public void notifyRemoteObservers(Object obj) throws RemoteException;
}
