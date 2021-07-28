import java.io.*;
import java.util.*;

public class PersistentClock implements Serializable, Runnable{
	private static final long serialVersionUID = 1;
	transient private Thread animator; // provere senza transient .... 
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
}
