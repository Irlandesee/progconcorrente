import java.io.*;
import java.util.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteListenerImpl implements RemoteListener{
	public void remoteEvent(Object param) throws RemoteException{
		System.err.println("Remote notification: "+param);
	}

	private void exec() throws Exception{
		String host = null;
		Registry req = LocateRegistry.getRegistry(host);
		Server s = (Server) registry.lookup("server");
		RemoteListener stub = 
			(RemoteListener) UnicastRemoteObject.exportObject(this, 3939);
		server.addListener(stub);
		for(int i = 0; i < 5; i++){
			System.err.println("chiamata remota: " + server.toUpperCase("test"+i));
			Thread.sleep(1000);
		}

		server.removeListener(this);
		UnicastRemoteObject.unexportedObject(this, false);
	}

	public static void main(String[] args) throws Exception{
		RemoteListenerImpl c = new RemoteListenerImpl();
		c.exec();
	}
}