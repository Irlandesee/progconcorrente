
public class Pool {
	private int size;
	private Chopstick[] sticks;
	private int numAvailable=0;
	private int numPhilosophers;
	private int [] philSticks;  // number of sticks allocated to each philosopher

	Pool(int numSticks, int numPhil) {
		this.size=numSticks;
		this.numPhilosophers=numPhil;
		sticks = new Chopstick[numSticks];
		for(int i=0; i<numSticks; i++)
			sticks[i]=new Chopstick(i+1);
		numAvailable=numSticks;
		philSticks = new int[numPhil];
		for(int i=0; i<numPhil; i++) {
			philSticks[i]=0;
		}
	}
	private boolean someoneEating() {
		for(int i=0; i<numPhilosophers; i++) {
			if(philSticks[i]==2)
				return true;
		}
		return false;
	}
	
	public synchronized int get(int philId) throws InterruptedException{
		while(numAvailable==0 || (numAvailable==1 && philSticks[philId]==0 && !someoneEating())){
			wait();
		}
		for(int i=0; i<size; i++){
			if(sticks[i].isAvaliable()){
				sticks[i].take();
				philSticks[philId]++;
				numAvailable--;
				return i;
			}
		}
		return -1; // never executed
	}

	public synchronized void free(int philId, int i){
		sticks[i].leave();
		philSticks[philId]--;
		numAvailable++;
		notifyAll();
	}
}
