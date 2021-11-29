import java.util.LinkedList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

public class Chef extends Thread{

	private Cucina c;
	private LinkedList<String> ordersToPrepare;
	private final int maxOrders = 4;


	public Chef(Cucina c){
		this.c = c;
		this.ordersToPrepare = new LinkedList<String>();
	}

	public void run(){
		while(true){
			while(ordersToPrepare.size() < maxOrders){
				String order = c.getNextOrder();
				ordersToPrepare.add(order);
			}
			Iterator<String> it = ordersToPrepare.iterator();
			while(it.hasNext()){
				String order = it.next().toString();
				System.out.printf("Chef preparing %s\n", order);
				c.prepare(order);
			}
			try{
				Thread.sleep(ThreadLocalRandom.current().nextInt(100, 300));
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
	}

}