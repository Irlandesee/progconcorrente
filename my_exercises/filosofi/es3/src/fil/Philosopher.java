package fil;

import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread{

	private Pool pool;
	private String name;

	public Philosopher(String id, Pool _pool){
		name = id;
		pool = _pool;
	}

	private void printAction(String action){
		System.out.printf("Phil %s: %s\n", name, action);
	}

	private void doAction(String s, int minTime, int maxTime){
		printAction(s);
		try{
			Thread.sleep(ThreadLocalRandom.current().nextInt(minTime, maxTime));
			state = Philosopher.Philstate.THINKING;
		}catch(InterruptedException ie){ie.printStackTrace();}
	}

	public void run(){
		while(true){
			doAction("thinking", 400, 500);
			printAction("hungry");
			pool.takeTwo(name);
			doAction("eating", 100, 200);
			printAction("freeing resources")
			pool.leaveTwo(name);
		}
	}

}