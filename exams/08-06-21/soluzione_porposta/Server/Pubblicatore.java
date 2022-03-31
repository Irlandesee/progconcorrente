import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Pubblicatore extends UnicastRemoteObject implements PubblicatoreInterf{

	private static final long serialVersionUID = 1L;
	Hashtable<TipoNotizia, Notizia> leNotizie;
	Hashtable<TipoNotizia, LinkedList<FruitoreNotizieInterf>> iClienti;
	TipoNotizia[] iTipi;
	final int numProduttori=6;
	Pubblicatore() throws RemoteException{
		super();
		leNotizie = new Hashtable<TipoNotizia, Notizia>();
		iClienti = new Hashtable<TipoNotizia, LinkedList<FruitoreNotizieInterf>>();
		iTipi=TipoNotizia.values();
		for(TipoNotizia tn : iTipi) {
			leNotizie.put(tn, new Notizia(tn, "----"));
			iClienti.put(tn, new LinkedList<FruitoreNotizieInterf>());
		}
	}
	public boolean inizioInteresse(FruitoreNotizieInterf f, TipoNotizia tn) throws RemoteException {
		System.out.println("Pubblicatore: inizio interesse per "+tn);
		LinkedList<FruitoreNotizieInterf> l;
		l=iClienti.get(tn);
		if(l==null) {
			return false;
		}
		synchronized(l) {
			if(!l.contains(f)) {
				l.add(f);
			}
		}
		return true;
	}
	public boolean cessaInteresse(FruitoreNotizieInterf f, TipoNotizia tn) throws RemoteException {
		System.out.println("Pubblicatore: fine interesse per "+tn);
		LinkedList<FruitoreNotizieInterf> l;
		l=iClienti.get(tn);
		if(l==null) {
			return false;
		}
		synchronized(l) {
			if(l.contains(f)) {
				l.remove(f);
			}
		}
		return true;
	}
	void notify(TipoNotizia tn) {
		LinkedList<FruitoreNotizieInterf> l=null;
		FruitoreNotizieInterf fr;
		Notizia notizia=null;
		notizia=leNotizie.get(tn);
		l=iClienti.get(tn);
		synchronized(l) {
			synchronized(notizia) {
				int numClienti=l.size();
				System.out.println("Pubblicatore: notifico notizia "+notizia.leggi()+
						" di tipo "+tn+" a "+numClienti+" clienti");
				int j=0;
				while(j<numClienti) {
					try {
						fr=l.get(j);
						System.out.println("Pubblicatore: notifico "+notizia.leggi()+" a "+fr.nome());
						fr.update(notizia);
						j++;
					} catch(IndexOutOfBoundsException | RemoteException e) {
						System.out.println("Pubblicatore: rimuovo cliente");
						l.remove(j);
						numClienti--;
					}
				}
				notizia.annulla();			
			}
		}
	}
	void printout() {
		for(TipoNotizia tt : iTipi) {
			System.out.println("Politica: "+leNotizie.get(tt).leggi());
		}
	}
	void exec() {
		ProduttoreNotizie pn[]=new ProduttoreNotizie[numProduttori];
		for(int i=0; i<numProduttori; i++) {
			pn[i]=new ProduttoreNotizie(i, leNotizie);
		}
		while(true) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {	}
			//printout();
			for(TipoNotizia tt : iTipi) {
				notify(tt);
			}
		}
	}

	public static void main(String[] args) throws RemoteException {
		Pubblicatore p = new Pubblicatore();
		Registry reg=LocateRegistry.createRegistry(1099);
		reg.rebind("DistribNotizie", p);
		p.exec();
	}
}

