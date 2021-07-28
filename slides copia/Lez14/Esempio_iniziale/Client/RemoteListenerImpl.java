import java.io.*;
import java.util.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteListenerImpl implements RemoteListener {
  public void remoteEvent(Object param) throws RemoteException {
    System.err.println("REMOTE NOTIFICATION: num calls = " + param);
  }
  private void exec() throws Exception {
    String host = null; // localhost
    Registry registry = LocateRegistry.getRegistry(host);
    Server server = (Server) registry.lookup("server");
    RemoteListener stub = (RemoteListener)UnicastRemoteObject.exportObject(this, 0);
    server.addListener(stub);
    for(int i=0; i<3; i++) {
      System.err.println("Chiamata remota: " +
                       server.toUpperCase("test "+i));
      Thread.sleep(100);
    }
    server.removeListener(this);
    UnicastRemoteObject.unexportObject(this, false); 
  }
  public static void main(String[] args) throws Exception {
    RemoteListenerImpl client = new RemoteListenerImpl();
    client.exec(); 
  }
}
