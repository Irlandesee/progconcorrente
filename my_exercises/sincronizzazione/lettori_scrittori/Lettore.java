import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Lettore extends Thread{

	private ArrayCondiviso ac;
	private int readerID;

	public Lettore(ArrayCondiviso ac, int readerID){
		this.ac = ac;
		this.readerID = readerID;
	}

	public void run(){
		//reads until there aren't numbers left
		int numbersLeft = ac.getCurrentSize();
		if(numbersLeft == 0){
			try{
				System.out.printf("Reader %d: no numbers in array\n", readerID);
				Thread.sleep(ThreadLocalRandom.current().nextInt(100, 300));
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}	
		while((numbersLeft = ac.getCurrentSize()) > 0){
			try{ //inizio zona critica
				ArrayCondiviso.full.acquire();
			}catch(InterruptedException ie2){
				System.out.printf("Reader %d: ie while trying to acquire lock on empty\n", readerID);
				ie2.printStackTrace();
			}
			int numberRead = ac.read(readerID);
			ArrayCondiviso.full.release(); //fine zona critica
			//System.out.printf("Reader %d left crit zone\n", readerID);
		}
		System.out.printf("Reader %d: current size %d, done reading\n", readerID, ac.getCurrentSize());
	}


}