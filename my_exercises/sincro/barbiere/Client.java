import java.util.concurrent.ThreadLocalRandom;

public class Client extends Thread{

	private int clientID;
	private Barber b;
	private boolean done;

	public Client(Barber b, int clientID){
		this.clientID = clientID;
		this.b = b;
		this.done = false;
	}

	public int getClientID(){
		return this.clientID;
	}

	public synchronized boolean getDone(){
		return this.done;
	}

	public synchronized void setDone(boolean done){
		this.done = done;
	}

	public void run(){
		if(b.getNumberOfClientsInShop() == b.maxClientsInShop){// non ce posto
			System.out.printf("Client %d no seats free, exiting the shop\n", clientID);
			/**
			try{
				Thread.current().join();
			}catch(InterruptedException ie){ie.printStackTrace();}
			**/
		}
		else{
			if(b.getAvailability() && b.getSleeping()){ //wake the barber up
				b.addClientToQueue(this);
				b.setAvailability(false);
				b.setSleeping(false);
				while(getDone() == false){
					try{
						Thread.sleep(ThreadLocalRandom.current().nextInt(100, 300));
					}catch(InterruptedException ie2){ie2.printStackTrace();}
				}
				
			}
			else{ //wait for the barber to be done
				b.addClientToQueue(this);
				while(getDone() == false){
					try{
						Thread.sleep(ThreadLocalRandom.current().nextInt(100, 400));
					}catch(InterruptedException ie4){ie4.printStackTrace();}
				}
				System.out.printf("Client %d exits the shop\n", clientID);
				/**
				try{
					Thread.current().join();
				}catch(InterruptedException ie3){ie3.printStackTrace();}
				**/
			}
		} 
	}

}