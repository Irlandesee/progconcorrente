import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
public interface PrezzoCarburante extends Remote {
	Distributore getInfoDistributore(String name) throws RemoteException;
	boolean setNewPrezzi(Distributore distributore) throws RemoteException;
	void addDistributore(Distributore distributore) throws RemoteException;
	List<String> getNomiDistributori() throws RemoteException;
	void shutdown() throws RemoteException;
}
