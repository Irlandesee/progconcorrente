import java.util.concurrent.ThreadLocalRandom;
public class Philosopher extends Thread {
	private Chopstick right, left;
	private String name;
	public Philosopher(String n,
			Chopstick left, Chopstick right) {
		this.name=n;
		this.left=left;
		this.right=right;
	}
	private void writeState(String action, String stickName) {
		System.out.println("Phil "+name+action+stickName);
	}
	public void run() {
		boolean b, gotSticks;
		while(true) {
			try {
				Thread.sleep(100);  // thinking
				writeState(": hungry", "");
				gotSticks=false;
				while(!gotSticks){
					b=left.take(0);
					if(b) {
						gotSticks=right.take(1);
						if(!gotSticks) {
							writeState(": leaving ", left.getName());
							left.leave();
							Thread.sleep(ThreadLocalRandom.current().nextInt(3, 30));
						} 						
					}
				}
				writeState(": eating", "");
				Thread.sleep(200);
				left.leave(); right.leave();
			} catch (InterruptedException e) {return ; }
		}
	}
}
