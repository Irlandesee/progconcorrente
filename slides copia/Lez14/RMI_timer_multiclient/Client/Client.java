import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements RemoteObserver {
	private static final long serialVersionUID = 1L;
	protected Client() throws RemoteException {
	}
	public void remoteUpdate(Object observable, Object arg) throws RemoteException {
		System.out.println("got message:" + arg);
	}
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry(1099);
			TimeTickService remoteService =(TimeTickService) registry.lookup("TimeTick");
			RemoteObserver client = new Client();
			remoteService.addRemoteObserver(client);
			remoteService.setPeriod(client, 5);
			Thread.sleep(20000);
			remoteService.setPeriod(client, 1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
