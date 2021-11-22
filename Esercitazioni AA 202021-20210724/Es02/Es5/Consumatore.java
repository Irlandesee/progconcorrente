import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Consumatore extends Thread {
	private BlockingQueue<String> theQueue;
	int myId;
	String v;
	public Consumatore(BlockingQueue<String> q, int id){
		this.theQueue=q;
		this.myId=id;
	}
	public void run(){
		for(;;){
			try {
				for(;;) {
					v=theQueue.poll();
					if(v!=null)
						break;
					System.out.println("Consumer "+myId+" faccio un pisolino ");
					Thread.sleep(ThreadLocalRandom.current().nextInt(10,30));
				}
			} catch (InterruptedException e1) {	}	
			System.out.println("Consumer "+myId+" read "+v);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e2) {	} // simula il tempo per il consumo
		}				
	}
}

