import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Barber extends Thread{

	private boolean isAvailable;
	private boolean isSleeping;
	private int numberOfClientsInShop;
	private LinkedBlockingQueue<Client> clientQueue;

	public final int maxClientsInShop = 20;

	public Barber(){
		this.isSleeping = true;
		this.isAvailable = true;
		this.numberOfClientsInShop = 0;
		clientQueue = new LinkedBlockingQueue<Client>(maxClientsInShop); 
	}

	public synchronized boolean getAvailability(){
		return this.isAvailable;
	}

	public synchronized void setAvailability(boolean availability){
		this.isAvailable = availability;
	}

	public synchronized boolean getSleeping(){
		return this.isSleeping;
	}

	public synchronized void setSleeping(boolean isSleeping){
		this.isSleeping = isSleeping;
	}

	public synchronized int getNumberOfClientsInShop(){
		return this.numberOfClientsInShop;
	}

	public synchronized void addNumberOfClientInShop(){
		this.numberOfClientsInShop++;
	}

	public synchronized void rmNumberOfClientInShop(){
		this.numberOfClientsInShop--;
	}

	public synchronized boolean addClientToQueue(Client c){
		if(getNumberOfClientsInShop() == maxClientsInShop)
			return false;
		else{ //there is space in the queue
			try{
				clientQueue.add(c);
				addNumberOfClientInShop();
				notify();
			}catch(IllegalStateException is){
				is.printStackTrace();
			}
			return true;
		}
	}

	public void run(){
		while(true){
			while(getNumberOfClientsInShop() == 0){  //no clients in the shop => goes back to sleep
				try{
					System.out.printf("Barber: no clients in shop, going to sleep\n");
					synchronized (this){
						setAvailability(true);
						setSleeping(true);
						wait(ThreadLocalRandom.current().nextInt(100, 300));
					}
				}catch(InterruptedException ie){
					ie.printStackTrace();
				}
			}
			while(getNumberOfClientsInShop() > 0){
				//serve one client at a time
				
				Client c;
				setAvailability(false);
				setSleeping(false);
				try{
					synchronized(clientQueue){
						c = clientQueue.poll();
						System.out.printf("Barber serving client %d\n", c.getClientID());
					}
					Thread.sleep(ThreadLocalRandom.current().nextInt(200, 400));
					c.setDone(true);
					System.out.printf("Barber is done with client:%d\n", c.getClientID());
				}catch(InterruptedException ie3){
					ie3.printStackTrace();
				}

				rmNumberOfClientInShop();
				
			}

		}
	}

}