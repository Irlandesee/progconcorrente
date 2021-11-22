import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Produttore extends Thread {
	private BlockingQueue<String> theQueue;
	int myId;
	String v;
	public Produttore(BlockingQueue<String> q, int id){
		this.theQueue=q;
		this.myId=id;
	}
	public void qualcosaDiUtile() {
		// metodo che simula qualche attivita` utile del produttore
		int n=0;
		for(int i=0; i<10000; i++) {
			for(int j=0; j<10000; j++) {
				n=1-n;
			}
		}
	}
	public void run(){
		int i=0;
		for(;;){
			try {
				Thread.sleep(100); // simula tempo produzione
			} catch (InterruptedException e2) {	}
			try {
				v="str_"+myId+"_"+(i++);
				for(;;) {
					if(theQueue.offer(v, 70, TimeUnit.MILLISECONDS))
						break;
					System.out.println("Producer "+myId+" faccio qualcosa ");
					qualcosaDiUtile();
				}
			} catch (Exception e1) { }	
			System.out.println("Producer "+myId+" wrote "+v);
		}				
	}
}

