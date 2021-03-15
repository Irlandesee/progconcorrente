public class Philosopher extends Thread {
	private Chopstick right, left;
	private String name ;
	public Philosopher(String id, Chopstick left, Chopstick right) {
		this.name=id;
		this.left=left;
		this.right=right;
	}
	private void writeState(String action, String stickName) {
		System.out.println("Phil "+name+action+stickName);
	}
	public void run() {
		while(true) {
			try {
				writeState(": thinking", "");
				Thread.sleep(30);
				writeState(": hungry", "");
				left.take();
				writeState(" picked up ", left.getName());
				right.take();
				writeState(" picked up ", right.getName());
				writeState(": eating", "");
				Thread.sleep(40);
				left.leave();
				writeState(" dropped ", left.getName());
				right.leave();
				writeState(" dropped ", right.getName());
			} catch (InterruptedException e) {return ; }
		}
	}
}


