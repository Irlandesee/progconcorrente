package fil;

import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread {
	private Pool pool;
	private String name ;

	private enum PhilState {
		HUNGRY, //ready
	 THINKING, //waiting
	  EATING} //running

	private PhilState state;

	public Philosopher(String id, Pool p) {
		this.name=id;
		this.pool=p;
		state = Philosopher.PhilState.THINKING;
	}

	private void printout(String s1) {
		System.out.println("Phil "+name+s1);
	}

	private void doActivity(String s, int minTime, int maxTime) {
		printout(s);
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(minTime, maxTime));
			state = Philosopher.PhilState.THINKING;
		} catch (InterruptedException e) {}		
	}

	public boolean isRunning(){
		return state == Philosopher.PhilState.EATING; 
	}

	public void run() {
		int c1, c2;
		while(true) {
			try {
				doActivity(" thinking ", 400, 500);
				printout(" hungry") ;
				state = Philosopher.PhilState.HUNGRY;
				while ((c1 = pool.get_one_of_many()) == -1){
					doActivity("thinking", 10, 15);
				}
				printout(" picked up first chopstick");
				while ((c2 = pool.get_one()) == -1){
					doActivity("thinking", 10, 15);
				}
				printout(" picked up second chopstick");
				doActivity(" eating ", 100, 200);
				state = Philosopher.PhilState.EATING;

				printout(" is going to free resources ");
				pool.free(c2);
				pool.free(c1);

			} catch (InterruptedException e) {return ; }
		}
	}
}
