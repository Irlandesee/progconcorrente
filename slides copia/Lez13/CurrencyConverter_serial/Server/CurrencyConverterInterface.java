import java.rmi.*;

public interface CurrencyConverterInterface extends Remote {
	Conversion compute(Conversion conv) throws RemoteException;
}
