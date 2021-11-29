
public class Pool {
	private int size;
	private Chopstick[] sticks;
	Pool(int numSticks) {
		this.size=numSticks;
		sticks = new Chopstick[numSticks];
		for(int i=0; i<numSticks; i++)
			sticks[i]=new Chopstick(i+1);
	}
	public synchronized int get_one() throws InterruptedException{
		for(int i=0; i<size; i++){
			if(sticks[i].isAvaliable()){
				sticks[i].take();
				return i;
			}
		}
		return -1;
	}
	public synchronized int get_one_of_many() throws InterruptedException{
		int free=0;
		for(int i=0; i<size; i++){
			if(sticks[i].isAvaliable()){
				free++;			
			}
		}
		if(free>1){
			return this.get_one();	
		}
		return -1;
	}
	public void free(int i){
		sticks[i].leave();
	}
}
