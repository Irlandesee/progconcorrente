import java.io.*;
import java.util.*;

public class PersistentClock implements Runnable{
	private Thread animator;
	private static final long serialVersionUID = 1;
	private long animationSpeed;
	public PersistentClock(int animSpeed) {
		this.animationSpeed = animSpeed;
		animator = new Thread(this);
		animator.start();
	}
	public void run() {
		while (true) {
			try {
				System.out.print(new Date()+"\r");
				Thread.sleep(animationSpeed);
			} catch (InterruptedException e) { }
		}
	}
	public static void main(String[] args) throws IOException{
		PersistentClock p = new PersistentClock(1000);
	}
}

