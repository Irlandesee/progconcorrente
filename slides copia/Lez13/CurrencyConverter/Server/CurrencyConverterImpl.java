import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CurrencyConverterImpl implements CurrencyConverter {
  public CurrencyConverterImpl() { }
  public static void main(String args[]) {
    try {
      CurrencyConverterImpl obj = new CurrencyConverterImpl();
      CurrencyConverter stub = (CurrencyConverter)UnicastRemoteObject.exportObject(obj, 3939);
      Registry registry = LocateRegistry.createRegistry(1099);
//      Registry registry = LocateRegistry.getRegistry();  // se registry preesiste
      registry.rebind("CurrencyConverter", stub);
      System.err.println("Server ready");
    } catch (Exception e) {
       System.err.println("Server exception: " + e.toString());
       e.printStackTrace();
    }
  }
  public float toEur(float usd) throws RemoteException {
     return usd*0.895415473F;
  }
  public float toUsd(float eur) throws RemoteException {
     return eur*1.114365F;
}}

