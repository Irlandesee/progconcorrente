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
		boolean taken=false;
		for(;;){
			taken=false;
			while(!taken) {
				v=theQueue.poll();
				if(v!=null) {
					System.out.println("Consumer "+myId+" read "+v);
					taken=true;
				} else {
					System.out.println("Consumer "+myId+" andata buca!");
					// andata male: la coda era vuota.
					// aspettiamo un po' prima di riprovare
					try {
						Thread.sleep(ThreadLocalRandom.current().nextInt(1,40));
					} catch (InterruptedException e2) {	}
				}
			}
			try {
				// questa sleep simula il fatto che il consumo richiede un certo tempo
				Thread.sleep(ThreadLocalRandom.current().nextInt(10,300));
			} catch (InterruptedException e) {	}
		}				
	}
}

