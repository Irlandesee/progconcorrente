import java.util.concurrent.*;
public class Producer implements Runnable {
	private BlockingQueue<Message> queue;
	public Producer(BlockingQueue<Message> q) {
		this.queue=q; 
	}
	public void run(){
		for(int i=0; i<100; i++) {
			Message msg = new Message ("dato_" + i);
			try {
				Thread.sleep(10);
				queue.put(msg);   // produce messages
				System.out.println(Thread.currentThread().getName()+" produced " + msg.getMsg());
			} catch (InterruptedException e) { }
		}
		try {
			queue.put(new Message ("exit"));
		} catch (InterruptedException e) { }
		System.out.println("Producer finished");
	}
}
