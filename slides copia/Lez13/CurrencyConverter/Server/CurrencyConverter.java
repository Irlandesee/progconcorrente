
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CurrencyConverter extends Remote {
  float toEur(float usd) throws RemoteException;
  float toUsd(float eur) throws RemoteException;
}

