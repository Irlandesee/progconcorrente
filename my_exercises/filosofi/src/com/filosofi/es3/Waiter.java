import java.util.concurrent.LinkedBlockingQueue;

public class Waiter extends Thread{

	private LinkedBlockingQueue<Request> requestsQueue;
	private int currentSize; //number of items present in this instant in the queue
	private LinkedBlockingQueue<Philosopher> philosophersRunning;

	private Philosopher[] philophers;
	private Chopstick[] chopsticks;

	public Waiter(Philosopher[] philophers, Chopstick[] chopsticks){
		requestsQueue = new LinkedBlockingQueue<Request>();
		philosophersRunning = new LinkedBlockingQueue<Philosopher>();

		this.philophers = philophers;
		this.chopsticks = chopstick;

		this.currentSize = 0;
	}

	public void requestTwo(Request r){
		System.out.printf("Waiter: adding %s to request queue", r.toString());
		try{
			requestsQueue.put(r);
		}catch(InterruptedException ie){
			ie.printStackTrace();
		}
	}

	public synchronized void leaveTwo(Chopstick i, Chopstick j){

	}

	public void run(){
		//no requests in queue
		while(requestsQueue.isEmpty()){
			try{
				wait(ThreadLocalRandom.current().nextInt(100, 300));
			}catch(InterruptedException ie){
				ie.printStackTrace();
			}
		}
		while(currentSize > 0){
			Request r;
			Philosopher p;
			Chopstick lRequested;
			Chopstick rRequested;
			try{
				r = requestsQueue.poll();
				System.out.println("Waiter examining: "+r.toString());
				p = philophers[r.getPhilID()];
				lRequested = chopsticks[r.getLeftChopstickID()];
				rRequested = chopsticks[r.getRightChopstickID()];
				if(!lRequested.isAvailable() && !rRequested.isAvailable()){ //can't execute
					System.out.printf("Phil %d can't execute now, putting request at the back\n", p.getID());
					requestsQueue.put(r);
				}
				else{
					System.out.printf("Phil %d added to execution list\n", p.getID());
					philosophersRunning.put(p);

				}
			}catch(InterruptedException ie2){
				ie2.printStackTrace();
			}
		}
	}

}