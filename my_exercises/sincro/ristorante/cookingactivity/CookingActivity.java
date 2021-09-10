package cookingactivity;

import java.util.concurrent.ThreadLocalRandom;

public class CookingActivity extends Thread{

	private Kitchen k;
	private String servedOrder;

	public CookingActivity(Kitchen _k, String o){
		k = _k;
		servedOrder = o;
		start();
	}

	public void run(){
		System.out.println("Started cooking: "+servedOrder.toString());
		try{
			Thread.sleep(ThreadLocalRandom.current().nextInt(400, 800));
		}catch(InterruptedException ie){}
		k.finished(servedOrder);

	}

}