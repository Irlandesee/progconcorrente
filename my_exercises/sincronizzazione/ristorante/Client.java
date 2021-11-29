import java.util.concurrent.ThreadLocalRandom;

public class Client extends Thread{

	private static String clientID = 0;
	private Cucina c;
	private final int maxNumberOrder = 100;

	public Client(Cucina c){
		this.c = c;
	}

	public void run(){
		String order = clientID++ + ThreadLocalRandom.current().nextInt(0, maxNumberOrder);
		c.putOrder();
		if(!(c.isOrderReady()){
			try{
				Thread.sleep(ThreadLocalRandom.current().nextInt(100, 300));
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
		System.out.printf("Client %d eats %s\n", clientID, order);
		try{
			Thread.sleep(ThreadLocalRandom.current().nextInt(2000, 3000));
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
		System.out.printf("Client %d exits the shop\n", clientID);

	}

}