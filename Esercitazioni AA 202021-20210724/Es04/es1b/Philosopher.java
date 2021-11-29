import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread {
	private Pool pool;
	private String name ;
	public Philosopher(String id, Pool p) {
		this.name=id;
		this.pool=p;
	}
	void printout(String s1) {
		System.out.println("Phil "+name+s1);
	}
	void doActivity(String s, int minTime, int maxTime) {
		printout(s);
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(minTime, maxTime));
		} catch (InterruptedException e) {}		
	}
	public void run() {
		int c1, c2;
		while(true) {
			try {
				doActivity(" thinking ", 400, 500);
				printout(" hungry") ;
				c1=pool.get_one_of_many();
				printout(" picked up first chopstick ("+c1+")");
				c2=pool.get_one();
				printout(" picked up second chopstick ("+c2+")");
				doActivity(" eating ", 100, 200);
				printout(" is going to free resources ("+c1+","+c2+")");
				pool.free(c2);
				pool.free(c1);
			} catch (InterruptedException e) {return ; }
		}
	}
}
