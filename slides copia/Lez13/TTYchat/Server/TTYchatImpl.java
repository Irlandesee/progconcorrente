import java.util.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.lang.Thread;

public class TTYchatImpl extends UnicastRemoteObject implements TTYchat {
	private static final long serialVersionUID = 1L;
	List<TTYchatClient> occupants;
	public TTYchatImpl() throws RemoteException {
		occupants = new ArrayList<TTYchatClient>();
	}
	public synchronized void enterRoom(TTYchatClient c) throws RemoteException {
		occupants.add(c);
	}
	public synchronized void  saySomething(String s, TTYchatClient cc) throws RemoteException {
		String message = cc.name()+": "+s;
		System.out.println(Thread.currentThread() + ":Server: got " +message);
		int numClients=occupants.size();
		int i=0;
		while(i<numClients) {
			TTYchatClient c=occupants.get(i);
			try {
				c.somethingSaid(message);
				i++;
			} catch (Exception x) {
				System.out.println("Someone left");
				occupants.remove(c);
				numClients--;
			}
		}
	}
	static public void main(String args[]) {
		try {
			TTYchatImpl obj = new TTYchatImpl();
			Registry registro = LocateRegistry.createRegistry(1099);
			registro.rebind("TTYCHAT", obj);
			System.out.println("TTYChat Server bound in registry");
		} catch (Exception e) {
			System.out.println("TTYChatServer err: " + e.getMessage());
		}
	}
}

