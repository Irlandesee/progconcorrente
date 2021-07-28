import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.util.Random;

public class CurrencyConverterClient {
	void exec() throws RemoteException, NotBoundException {
		Conversion conv;
		Random rnd=new Random();
		int times=2+rnd.nextInt(10);
		String fromCurrency, toCurrency;
		
		Registry registro = LocateRegistry.getRegistry(1099);
		CurrencyConverterInterface stub = 
				(CurrencyConverterInterface) registro.lookup("CurrencyConverter");
		for(int i=0; i<times; i++) {
			try {
				if(rnd.nextBoolean()) {
					fromCurrency="USD";
					toCurrency="EUR";
				} else {
					fromCurrency="EUR";
					toCurrency="USD";
				}
				conv = new Conversion(rnd.nextFloat()*100, fromCurrency, toCurrency);
				conv=stub.compute(conv);
				System.out.println(conv);
			} catch (Exception e) {
				System.err.println("Client exc.: " + e.toString());
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		try {
			new CurrencyConverterClient().exec();
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
