import java.util.concurrent.ThreadLocalRandom;

public class Philosopher extends Thread {
	private Chopstick right, left;
	private String name ;
	private Waiter myWaiter;
	public Philosopher(String id, Waiter w,
			Chopstick left, Chopstick right) {
		this.name=id;
		this.left=left;
		this.right=right;
		this.myWaiter=w;
	}
	private void writeState(String action, String stickName) {
		System.out.println("Phil "+name+action+stickName);
	}
	public void run() {
		while(true) {
			try {
				Thread.sleep(30);
				Thread.sleep(ThreadLocalRandom.current().nextInt(20,50));
				myWaiter.takeTwo(left, right);
				writeState(": eating", "");
				Thread.sleep(10,30);
				myWaiter.leaveTwo(left, right);
			} catch (InterruptedException e) {return ; }
		}
	}
}


