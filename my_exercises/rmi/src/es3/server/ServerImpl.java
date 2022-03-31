import java.io.*;
import java.util.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements Server{
	private List<RemoteListener> listeners = new ArrayList<RemoteListener>();
	private int nChiamate = 0;
	public ServerImpl() throws RemoteException{}

	public String toUpperCase(String x) throws RemoteException{
		nChiamate++;
		return x.toUpperCase();
	}

	public void addListener(RemoteListener listener) throws RemoteException{
		listeners.add(listner);
	}

	public void removeListener(RemoteListener listener) throws RemoteException{
		listeners.remove(listener);
	}

	private void notifyListeners(){
		for(RemoteListener l : listeners){
			try{
				l.removeEvent(nChiamate);
			}catch(RemoteException ee){listenrs.remove(l);}
		}
	}

	public static void main(String[] args) throws Exception{
		ServerImpl server = new ServerImpl();
		System.err.println("Registering");
		Registry reg = LocateRegistry.createRegistry(1099);
		reg.rebing("server", server);
		Ssytem.err.println("registered");
		while(true){
			server.notifyListeners();
			Thread.sleep(500);
		}
	}

}