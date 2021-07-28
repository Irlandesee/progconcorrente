import java.io.*;
import java.util.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements Server {
  private List<RemoteListener> listeners = new ArrayList<RemoteListener>();
  private int numChiamate = 0;
  public ServerImpl() throws RemoteException {
    super();
  }
  public String toUpperCase(String x) throws RemoteException {
    numChiamate++;
    return x.toUpperCase();
  }
  public void addListener(RemoteListener listener) throws RemoteException {
    listeners.add(listener);
  }
  public void removeListener(RemoteListener listener) throws RemoteException {
    listeners.remove(listener);
  }
  public void notifyListeners() {
    for (RemoteListener l : listeners) {
      try {
        l.remoteEvent(numChiamate);
      } catch (RemoteException ee) {
        listeners.remove(l);
      }
    }
  }
  public static void main(String[] args) throws Exception {
    ServerImpl server = new ServerImpl();
    System.err.println("Registering...");
    Registry registry = LocateRegistry.getRegistry();
    registry.rebind("server", server);
    System.err.println("Registered");
    while(true) {
      server.notifyListeners();
      Thread.sleep(50);
    }
  }
}

