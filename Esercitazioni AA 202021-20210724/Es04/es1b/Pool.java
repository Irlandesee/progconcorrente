
public class Pool {
	private int size;
	private Chopstick[] sticks;
	private int numAvailable=0;
	Pool(int numSticks) {
		this.size=numSticks;
		sticks = new Chopstick[numSticks];
		for(int i=0; i<numSticks; i++)
			sticks[i]=new Chopstick(i+1);
		numAvailable=numSticks;
	}
	public synchronized int get_one() throws InterruptedException{
		while(numAvailable==0){
			wait();
		}
		for(int i=0; i<size; i++){
			if(sticks[i].isAvaliable()){
				sticks[i].take();
				numAvailable--;
				return i;
			}
		}
		return -1;
	}
	public synchronized int get_one_of_many() throws InterruptedException{
		while(numAvailable<2){
			wait();
		}
		for(int i=0; i<size; i++){
			if(sticks[i].isAvaliable()){
				sticks[i].take();
				numAvailable--;
				return i;
			}
		}
		return -1;
	}
	public synchronized void free(int i){
		sticks[i].leave();
		numAvailable++;
		notifyAll();
	}
}