import java.util.concurrent.ThreadLocalRandom;

public class MakeDeadlock {
	public static void main(String arsg[]){
		Object a = new Object();
		Object b = new Object();
		Thread t1=new Thread(new LockObjects(a, b));
		Thread t2=new Thread(new LockObjects(b, a));
		t1.start(); t2.start();
	}
}

