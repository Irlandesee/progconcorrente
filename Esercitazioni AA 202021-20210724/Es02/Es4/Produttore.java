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
		boolean added=false;
		for(;;){
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(10,30));
			} catch (InterruptedException e1) {	}
			v="str_"+myId+"_"+(i++);
			added=false;
			while(!added) {
				try {
					added=theQueue.add(v);
					System.out.println("Producer "+myId+" wrote "+v);
				} catch(IllegalStateException ex) {
					System.out.println("Producer "+myId+" andata buca!");
					// si arriva qui quando add fallisce: aspettiamo un po' prima di riprovare
					try {
						Thread.sleep(ThreadLocalRandom.current().nextInt(1,40));
					} catch (InterruptedException e2) {	}
				}
			}
		}				
	}
}

