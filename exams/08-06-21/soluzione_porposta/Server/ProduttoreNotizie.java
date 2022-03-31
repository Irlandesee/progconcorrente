import java.util.Hashtable;
import java.util.concurrent.ThreadLocalRandom;

public class ProduttoreNotizie extends Thread {
	int mioId;
	Hashtable<TipoNotizia, Notizia> leNotizie;
	TipoNotizia[] iTipi;
	public ProduttoreNotizie(int i, Hashtable<TipoNotizia, Notizia> l) {
		mioId=i;
		leNotizie=l;
		iTipi=TipoNotizia.values();
		start();
	}
	public void run() {
		String x;
		int numTipi=TipoNotizia.values().length;
		int idNotizia=0;
		while(true) {
			x="blabla"+mioId+"__"+idNotizia++;
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
			} catch (InterruptedException e) {	}
			int cas=ThreadLocalRandom.current().nextInt(0, numTipi);
			TipoNotizia tn=iTipi[cas];
			leNotizie.get(tn).incrementa(x);
		}
	}
}

