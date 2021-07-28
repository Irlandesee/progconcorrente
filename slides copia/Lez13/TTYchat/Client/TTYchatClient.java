import java.rmi.Remote; 
import java.rmi.RemoteException; 

public interface TTYchatClient extends Remote {
  void somethingSaid(String something) throws RemoteException;
  String name() throws RemoteException;
}

