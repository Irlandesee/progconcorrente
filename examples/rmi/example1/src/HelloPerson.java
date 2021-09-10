import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloPerson extends Remote{

	public String sayHello() throws RemoteException;
	public String sayHello(Person p) throws RemoteException;

}