import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PubblicatoreInterf extends Remote {
	public boolean inizioInteresse(FruitoreNotizieInterf f, TipoNotizia tn) throws RemoteException;
	public boolean cessaInteresse(FruitoreNotizieInterf f, TipoNotizia tn) throws RemoteException;
}
