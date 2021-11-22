import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Consumatore extends Thread {
	private BlockingQueue<String> theQueue;
	int myId;
	String v;
	public Consumatore(BlockingQueue<String> q, int id){
		this.theQueue=q;
		this.myId=id;
	}
	public void qualcosaDiUtile() {
		// metodo che simula qualche attivita` utile del consumatore
		int n=0;
		for(int i=0; i<10000; i++) {
			for(int j=0; j<10000; j++) {
				n=1-n;
			}
		}
	}
	public void run(){
		for(;;){
			try {
				for(;;) {
					v=theQueue.poll(55, TimeUnit.MILLISECONDS);
					if(v!=null)
						break;
					System.out.println("Producer "+myId+" faccio qualcosa ");
					qualcosaDiUtile();
				}
			} catch (InterruptedException e1) {	}	
			System.out.println("Consumer "+myId+" read "+v);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e2) {	} // simula il tempo per il consumo
		}				
	}
}

