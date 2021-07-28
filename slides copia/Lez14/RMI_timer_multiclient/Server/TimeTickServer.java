import java.util.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class TimeTickServer implements RemoteObservable, TimeTickService {
  private Hashtable<RemoteObserver, TimePair> observers=new Hashtable<RemoteObserver, TimePair>();
  public TimeTickServer() {
  }
  public synchronized void addRemoteObserver(RemoteObserver o) throws RemoteException {
    observers.put(o, new TimePair(-99));
    System.out.println("Added observer:" + o);
  }
  public synchronized void removeRemoteObserver(RemoteObserver o) throws RemoteException {
    observers.remove(o);
  }
  public synchronized void setPeriod(RemoteObserver o, int period) throws RemoteException {
    if(observers.containsKey(o)) {
      observers.put(o,  new TimePair(period));
    }
  }
  public synchronized void notifyRemoteObservers(Object obj) {
    TimePair tp=null;
    Enumeration<RemoteObserver> keys = observers.keys();
    while(keys.hasMoreElements()){
      RemoteObserver key = keys.nextElement();
      tp=observers.get(key);
      tp.incElapsed();
      if(tp.getElapsed()==tp.getPeriod()) {
        tp.setElapsed(0);
        observers.put(key, tp);
        try {
          key.remoteUpdate(this, tp.getPeriod());
        } catch(RemoteException e) {
          observers.remove(key);
        }
      } else {
        observers.put(key, tp);
      }
    }
  }
  private void exec() {
    TimeTickService stub;
    try {
      stub = (TimeTickService) UnicastRemoteObject.exportObject(this, 3939);
      Registry registry = LocateRegistry.createRegistry(1099);
      registry.rebind("TimeTick", stub);
    } catch (RemoteException e1) {
      System.err.println("Server unable to start");
      System.exit(0);
    }
    System.err.println("Server ready");
    while(true) {
      try { Thread.sleep(1000); } catch (InterruptedException e) { }
      notifyRemoteObservers(null);
    }
  }
  public static void main(String[] args) {
    TimeTickServer obj = new TimeTickServer();
    obj.exec();
  }
}

