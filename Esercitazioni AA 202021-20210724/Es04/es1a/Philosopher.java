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
				while ((c1=pool.get_one_of_many())==-1){
					doActivity(" doing something ", 10, 15);
				}
				printout(" picked up first chopstick");
				while ((c2=pool.get_one())== -1){
					doActivity(" doing something ", 10, 15);
				}
				printout(" picked up second chopstick");
				doActivity(" eating ", 100, 200);
				printout(" is going to free resources ");
				pool.free(c2);
				pool.free(c1);
			} catch (InterruptedException e) {return ; }
		}
	}
}
