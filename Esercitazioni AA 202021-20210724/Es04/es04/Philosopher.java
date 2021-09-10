import java.util.concurrent.*;

public class Philosopher extends Thread{
	enum PhilosopherState {Thinking, Hungry, Eating};
	private int id ;
	PhilosopherState myState;
	Chopstick left, right;
	public Philosopher(int id, Chopstick l, Chopstick r) {
		this.id=id;
		myState=PhilosopherState.Thinking;
		left=l;
		right=r;
		printout(" created with left "+left.getId()+" right "+right.getId());
	}
	public int getID() {
		return this.id;
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
			myState=PhilosopherState.Thinking;
			doActivity(" thinking ", 100, 500);
			myState=PhilosopherState.Hungry;
			printout(" hungry");
			left.get(id);
			right.get(id);
			myState=PhilosopherState.Eating;
			doActivity(" eating ****************** ", 50, 200);
			left.leave();
			right.leave();
		}

	}

}
