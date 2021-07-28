import java.util.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class TimeTickServer implements RemoteObservable, TimeTickService {
	private List<RemoteObserver> observers = new ArrayList<RemoteObserver>();
	private int tickPeriod=-99;
	public TimeTickServer() {
	}
	public void addRemoteObserver(RemoteObserver o) throws RemoteException {
		observers.add(o);
		System.out.println("Added observer:" + o);
	}
	public void removeRemoteObserver(RemoteObserver o) throws RemoteException {
		observers.remove(o);
	}
	public void notifyRemoteObservers(Object obj) throws RemoteException {
		RemoteObserver o=null;
		System.out.println("server notifies");
		int numObservers=observers.size();
		int i=0;
		while(i<numObservers) {
			o=observers.get(i);
			try {
				o.remoteUpdate(this, obj);
				i++;
			} catch(RemoteException e) {
				observers.remove(o);
				if((--numObservers)==0) {
					tickPeriod=-99;
				}
				// throw e;
			}
		}
	}
	public void setTick(int period) throws RemoteException {
		tickPeriod = period;
	}
	private void exec() {
		long target, now;
		try {
			TimeTickService stub = (TimeTickService) UnicastRemoteObject.exportObject(this, 3939);
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("TimeTick", stub);
			System.err.println("Server ready");
			while(true) {
				target=1000*tickPeriod+System.currentTimeMillis();
				while(tickPeriod<0 || (now=System.currentTimeMillis())<target) {
					Thread.sleep(500);
				}
				notifyRemoteObservers(now);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public static void main(String[] args) {
		TimeTickServer obj = new TimeTickServer();
		obj.exec();
	}
}

