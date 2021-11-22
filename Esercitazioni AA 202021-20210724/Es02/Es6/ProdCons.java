import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProdCons {
	public final static int BUFSIZE=4;
	public final static int NumCons=2;
	public final static int NumProd=2;
	public static void main(String[] args) {
		BlockingQueue<String> queue= new ArrayBlockingQueue<String>(BUFSIZE);
		Thread Produttori[] = new Produttore[NumProd];
		Thread Consumatori[] = new Consumatore[NumCons];
		for(int i =0; i<NumProd; i++){
			Produttori[i]=new Produttore(queue, i);
			Produttori[i].start();
		}
		for(int i =0; i<NumCons; i++){
			Consumatori[i]=new Consumatore(queue, i);
			Consumatori[i].start();
		}
	}
}


