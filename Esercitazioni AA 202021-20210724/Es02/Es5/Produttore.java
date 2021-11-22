import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Produttore extends Thread {
	private BlockingQueue<String> theQueue;
	int myId;
	String v;
	public Produttore(BlockingQueue<String> q, int id){
		this.theQueue=q;
		this.myId=id;
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
					if(theQueue.offer(v))
						break;
					System.err.println("Producer "+myId+" faccio un pisolino ");
					Thread.sleep(ThreadLocalRandom.current().nextInt(10,300));
				}
			} catch (Exception e1) { }	
			System.out.println("Producer "+myId+" wrote "+v);
		}				
	}
}

