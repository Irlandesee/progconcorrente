import java.util.concurrent.*;

public class Philosopher extends Thread {
	private int id ;
	private Pool myWaiter;
	public Philosopher(int id, Pool w) {
		this.id=id;
		this.myWaiter=w;
	}
	void printout(String s1) {
		System.out.println("Phil "+id+s1);
	}
	void doActivity(String s, int minTime, int maxTime) {
		printout(s);
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(minTime, maxTime));
		} catch (InterruptedException e) {}		
	}
	public void run() {
		while(true) {
			doActivity(" thinking ", 400, 500);
			printout(" hungry") ;
			myWaiter.takeTwo(id);
			doActivity(" eating ", 100, 200);
			printout(" is going to free resources");
			myWaiter.leaveTwo(id);
		}
	}
}

