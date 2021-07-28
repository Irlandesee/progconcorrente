import java.rmi.Remote; 
import java.rmi.RemoteException; 

public interface TTYchat extends Remote {
	void enterRoom(TTYchatClient client) throws RemoteException;
	void exitRoom(TTYchatClient client) throws RemoteException;
	void saySomething(String something, TTYchatClient speaker) throws RemoteException;
}

