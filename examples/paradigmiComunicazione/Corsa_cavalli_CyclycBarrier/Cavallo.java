import java.util.concurrent.*;

public class Cavallo extends Thread {
	private int numGiri;
	CyclicBarrier barriera;
	public Cavallo(int id, CyclicBarrier b, int n) {
		barriera=b;
		setName("Cavallo_" + id);
		numGiri=n;
	}
	public void run() {
		for(int i=0; i<numGiri; i++) {
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
				System.out.println(getName()+" sta per entrare nella barriera ");
				barriera.await();
				System.out.println(getName()+ " e` ripartito!");
			} catch (InterruptedException | BrokenBarrierException e) {} 
		}
		System.out.println(getName()+" termina");
	}
}

