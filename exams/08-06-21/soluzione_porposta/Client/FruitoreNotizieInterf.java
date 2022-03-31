import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FruitoreNotizieInterf extends Remote{
	void update(Notizia s) throws RemoteException;
	String nome() throws RemoteException;
}
