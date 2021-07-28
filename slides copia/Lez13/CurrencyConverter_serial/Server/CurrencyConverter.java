
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

public class CurrencyConverter extends UnicastRemoteObject implements CurrencyConverterInterface {
	private static final long serialVersionUID = 1L;
	public CurrencyConverter() throws RemoteException {
		super();
	}
	private float toEur(float usd) {
		return usd*0.895415473F;
	}
	private float toUsd(float eur) {
		return eur*1.114365F;
	}
	public Conversion compute(Conversion conv) {
		float convertedAmount=0;
		if(conv.givenCurrency.equals("USD")) {
			convertedAmount=toEur(conv.getGivenAmount());
		}
		if(conv.givenCurrency.equals("EUR")) {
			convertedAmount=toUsd(conv.getGivenAmount());
		}
		conv.setTargetAmount(convertedAmount);
		return conv;
	}
	public static void main(String args[]) {
		try {
			CurrencyConverter obj = new CurrencyConverter();
			Registry registro = LocateRegistry.createRegistry(1099);
			registro.rebind("CurrencyConverter", obj);
			System.err.println("Server ready");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}
