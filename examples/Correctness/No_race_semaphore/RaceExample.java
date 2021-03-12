import java.util.concurrent.Semaphore;

public class RaceExample extends Thread {
	private Counter myCounter;
	private Semaphore theSemaphore;
	public RaceExample(Counter c, Semaphore s){
		myCounter=c;
		theSemaphore=s;
	}
	public void run() {
		for(int i=0; i<10000; i++) {
			try {
				theSemaphore.acquire();
			} catch (InterruptedException e){ }
			myCounter.add(1);
			theSemaphore.release();
		}
	}

	public static void main(String args[])
			throws InterruptedException {
		Counter counter = new Counter();
		Semaphore sem = new Semaphore(1);
		RaceExample p1 = new RaceExample(counter, sem);
		RaceExample p2 = new RaceExample(counter, sem);
		p1.start(); 
		p2.start();
		p1.join();
		p2.join();
		System.out.println("Counter = " + counter.count);
	}
}


