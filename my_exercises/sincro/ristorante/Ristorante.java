import java.util.concurrent.ThreadLocalRandom;

import chef.Chef;
import client.Client;
import cookingactivity.CookingActivity;
import kitchen.Kitchen;

public class Ristorante{

	public static void main(String[] args) throws InterruptedException{
		Kitchen k = new Kitchen();
		Chef c = new Chef(k);
		int nClients = 10 + (int) (Math.random()*20);
		Thread[] clients = new Thread[nClients];
		System.out.println("main: crating "+nClients+" clients");
		for(int i = 0; i < nClients; i++){
			clients[i] = new Client("Client_"+i, k);
			clients[i].start();
			try{
				Thread.sleep(ThreadLocalRandom.current().nextInt(100, 400));
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
		for(int i = 0; i < nClients; i++){
			try{
				clients[i].join();
			}catch(InterruptedException ie){}
		}
		c.interrupt(); //sig to shut down
		System.out.println("Closing down");
	}	

}