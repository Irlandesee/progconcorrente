package solution;

import java.util.concurrent.ThreadLocalRandom;

public class Phil extends Thread{


	private ChopStick left, right;
	private String name;
	private Waiter waiter;

	public Phil(String name, ChopStick left, ChopStick right, Waiter w){
		this.name = name;
		this.left = (left.getId() < right.getId()) ? left : right;
		this.right = (left.getId() < right.getId()) ? right : left;
		this.waiter = w;
		
	}

	public void run(){
		while(true){
			try{
				Thread.sleep(30);
				Thread.sleep(ThreadLocalRandom.current().nextInt(20, 60));
				waiter.takeTwo(left, right);
				writeState("eating", "");
				Thread.sleep(ThreadLocalRandom.current().nextInt(10, 30));
				waiter.leaveTwo(left, right);

			}catch(InterruptedException e){ return ; }
		}
	}

	private void writeState(String action, String stickName){
		System.out.printf("Phil %s: %s, %s\n", this.name, action, stickName);
	}

}