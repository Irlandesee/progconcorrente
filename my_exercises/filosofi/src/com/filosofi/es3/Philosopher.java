import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread{

	private Chopstick right, left;
	private int id;
	private Waiter w;

	public Philosopher(int id, Waiter w, Chopstick left, Chopstick right){
		this.id = id;
		this.left = left;
		this.right = right;
		this.w = w;
	}

	public void run(){
		try{
			Thread.sleep(ThreadLocalRandom.current().nextInt(30, 50));
			w.takeTwo(left, right);
			System.out.printf("Phil: %d eating\n", this.id);
			Thread.sleep(ThreadLocalRandom.current().nextInt(10 ,40));
			w.leaveTwo(left, right);
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
	}

	public int getID(){
		return this.id;
	}

}