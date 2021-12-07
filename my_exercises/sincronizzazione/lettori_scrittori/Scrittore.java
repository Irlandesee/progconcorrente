import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Scrittore extends Thread{

	private ArrayCondiviso ac;
	private int writerID;

	public Scrittore(ArrayCondiviso ac, int writerID){
		this.ac = ac;
		this.writerID = writerID;
	}

	private int nextInt(){
		return ThreadLocalRandom.current().nextInt(0, 100);
	}

	public void run(){
		int numDaGenerare = 10;
		while(numDaGenerare > 0){
			int num = nextInt();
			numDaGenerare--;
			try{ //inizio zona critica
				ArrayCondiviso.empty.acquire();
				//System.out.printf("Writer %d: entered crit zone\n", writerID);
			}catch(InterruptedException ie){
				System.out.printf("Writer %d: ie while trying acquire lock on empty\n", writerID);
				ie.printStackTrace();
			}
			ac.write(num, writerID);
			ArrayCondiviso.empty.release(); //fine zona critica
			//System.out.printf("Writer %d: left crit zone\n", writerID);
		}
		System.out.printf("Writer %d: done generating numbers\n", writerID);
	}

}