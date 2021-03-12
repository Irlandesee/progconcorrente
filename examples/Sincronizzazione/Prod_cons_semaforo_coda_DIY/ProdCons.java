import java.util.concurrent.Semaphore;
public class ProdCons {
	private static final int bufsize=4;
	public static final Semaphore mutex = new Semaphore(1);
	public static final Semaphore full = new Semaphore(0);
	public static final Semaphore empty = new Semaphore(bufsize);
	public static void main(String[] args) {
		CodaCondivisa cella=new CodaCondivisa(bufsize);
		new Produttore("Prod-1", cella).start();
		new Consumatore("Cons-1", cella).start();
		new Produttore("Prod-2", cella).start();
		new Consumatore("Cons-2", cella).start();
	}
}
