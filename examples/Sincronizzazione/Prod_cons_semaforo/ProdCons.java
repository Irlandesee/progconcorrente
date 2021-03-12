import java.util.concurrent.Semaphore;
public class ProdCons {
	public static final Semaphore mutex = new Semaphore(1);
	public static final Semaphore full = new Semaphore(0);
	public static final Semaphore empty = new Semaphore(CellaCondivisa.BUFFERSIZE);
	public static void main(String[] args) {
		CellaCondivisa cella=new CellaCondivisa();
		new Produttore("Prod", cella).start();
		new Consumatore("Cons", cella).start();
	}
}

