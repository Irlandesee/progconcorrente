import java.util.concurrent.Semaphore;
public class ProdCons {
	private static final int bufsize=5;
	public static final Semaphore mutex = new Semaphore(1);
	public static final Semaphore full = new Semaphore(bufsize);
	public static final Semaphore empty = new Semaphore(bufsize);

	public static void main(String[] args) {
		full.drainPermits();
		CellaCondivisaCoda cella = 
				new CellaCondivisaCoda(bufsize);
		new Produttore(cella).start();
		new Produttore(cella).start();
		new Consumatore(cella).start();
		new Consumatore(cella).start();
	}
}


