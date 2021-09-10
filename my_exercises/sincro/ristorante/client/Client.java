package client;

import java.util.concurrent.ThreadLocalRandom;

public class Client extends Thread{

	private String name;
	private String myOrder;
	private Kithen k;

	public Client(String _name, Kitchen _k){
		name = _name;
		k = _k;
		this.setName(name);
	}

	public void run(){
		myOrder = "order_"+name;
		System.out.printf("%s placing order.", name)
		k.putOrder(myOrder);
		k.isOrderReady(myOrder);
		System.out.println(name + " eating: " +myOrder);
		try{
			Thread.sleep(ThreadLocalRandom.current().nextInt(4000));
		}catch(InterruptedException ie){}
		System.out.println(name + ": finished eating.");
	}

}