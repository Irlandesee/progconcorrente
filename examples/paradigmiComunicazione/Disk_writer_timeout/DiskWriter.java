import java.util.concurrent.ThreadLocalRandom;


public class DiskWriter extends Thread {
	private PersistentSignal forSignalling;
	public DiskWriter(PersistentSignal s){
		this.forSignalling = s;
	}
	public void run(){
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(300, 400));
		} catch (InterruptedException e) { }
		forSignalling.send();
	}
}
