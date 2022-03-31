import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.concurrent.ThreadLocalRandom;

public class FruitoreNotizie extends UnicastRemoteObject implements FruitoreNotizieInterf {
	private static final long serialVersionUID = 1L;
	String mioNome;
	TipoNotizia[] iTipi;
	FruitoreNotizie(String s) throws RemoteException{
		super();
		iTipi=TipoNotizia.values();
		mioNome=s;
	}
	public void update(Notizia notizia) {
		System.out.println("fruitore_"+mioNome+" ha ricevuto una notizia di "+notizia.leggiTipo());
		System.out.println("fruitore_"+mioNome+": la notizia e`: "+notizia.leggi());
	}
	void exec(PubblicatoreInterf publ) throws RemoteException, NotBoundException {
		int numTipi=TipoNotizia.values().length;
		boolean esito=false;
		while(true) {
			System.out.println("Fruitore "+mioNome+": in ciclo");
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(0, 6000));
			} catch (InterruptedException e) { }
			int cas=ThreadLocalRandom.current().nextInt(0, numTipi);
			if(ThreadLocalRandom.current().nextBoolean()) {
				esito=publ.inizioInteresse(this, iTipi[cas]);
			} else {
				esito=publ.cessaInteresse(this, iTipi[cas]);
			}
			System.out.println("Fruitore "+mioNome+": operazione andata "+(esito?"":"non")+" bene");
		}
	}

	public static void main(String args[]) throws RemoteException, NotBoundException {
		FruitoreNotizie fr=null;
		if(args.length==0) {
			fr = new FruitoreNotizie("Default");
			//			System.err.println("Fruitore: manca parametro");
			//			System.exit(0);
		} else {
			fr = new FruitoreNotizie(args[0]);			
		}
		Registry reg=LocateRegistry.getRegistry(1099);
		PubblicatoreInterf publ=(PubblicatoreInterf) reg.lookup("DistribNotizie");
		fr.exec(publ);
	}
	public String nome() throws RemoteException {
		return mioNome;
	}
}
