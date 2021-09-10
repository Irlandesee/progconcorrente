package fil;

public class Pool {

	private int size;
	private Chopstick[] sticks;
	private Philosopher[] phils;
	private int philCount;

	public Pool(int numSticks) {
		this.size=numSticks;
		
		sticks = new Chopstick[numSticks];
		for(int i=0; i<numSticks; i++)
			sticks[i]=new Chopstick(i+1);
		phils = new Philosopher[Table.getNumberPhils()];
		philCount = 0;
	}

	public synchronized int get_one() throws InterruptedException{
		//se ce un ultima risorsa disponibile, ed è l'ultima che ti manca, puoi prenderla
		for(int i = 0; i < size; i++){
			if(sticks[i].isAvaliable()){
				sticks[i].take();
				return i;
			}
		}
		return -1;
	}

	public synchronized int get_one_of_many() throws InterruptedException{
		int free=0;
		for(int i = 0; i < size; i++){
			if(sticks[i].isAvaliable()){
				free++;			
			}
		}

		//se ce una risorsa disponibile e ce un altro processo che sta usando le risorse
		// puoi prenderla
		for(Philosopher p : phils){
			if(p.isRunning()){
				return this.get_one();
			}
		}

		if(free > 1){ //se ce una risorsa disponibile e non è l'unica disponibile, puoi prenderla
			return this.get_one();	
		}

		return -1;
	}

	public void free(int i){
		sticks[i].leave();
	}

	public void addPhil(Philosopher p){
		if(philCount < Table.getNumberPhils())
			phils[philCount] = p;
		philCount++;
	}

}
