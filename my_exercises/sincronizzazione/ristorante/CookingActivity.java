import java.util.concurrent.ThreadLocalRandom;

public class CookingActivity extends Thread{

	private String order;
	private Cucina c;
	public CookingActivity(Cucina c, String order){
		this.order = order;
		this.c = c;
		this.start();
	}

	public void run(){
		System.out.println("Cooking order: "+order);
		try{
			Thread.sleep(ThreadLocalRandom.current().nextInt(100, 300));
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
		c.finished();
	}

}