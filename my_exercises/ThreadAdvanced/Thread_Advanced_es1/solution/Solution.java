package solution;

public class Solution{

	private int[] buffer;
	private Producer producer;
	private Consumer consumer;

	private int bufferSize;
	private int numProducers;
	private int numConsumer;

	private int emptySpots;

	public Solution(int bufferSize, int numProducers, int numConsumers){
		this.bufferSize = bufferSize;
		this.numProducers = numProducer;
		this.numConsumers = numConsumers;
		
		buffer = new int[this.bufferSize];
		initBuffer();
		//Producer prod = new Producer(10, this);
		//Consumer cons = new Consumer();
	}

	/**
	 * Initialises the buffer,
	 * setting every position as "open -> "-1".
	 * */
	private void initBuffer(){
		for(int i = 0; i < bufferSize; i++)
			buffer[i] = -1;
	}

	/**
	 * Returns a copy of the buffer
	 * Not Thread safe
	 * */
	public int[] getBuffer(){
		return this.buffer;
	}

	/**
	 * Not Thread Safe
	 * */
	public void setBuffer(int[] buffer){
		this.buffer = buffer;
	}

	public int getBufferSize(){
		return this.bufferSize;
	}

	public int getNumProducers(){
		return this.numProducers;
	}

	public int getNumConsumers(){
		return this.numConsumers;
	}
	
	/**
	 * Returns the number of empty spots present 
	 * in the array
	 * */
	public synchronized int getEmptySpots(){
		return this.emptySpots;
	}

	/**
	 * Removes 1 empty spot 
	 * */
	public synchronized void removeEmptySpot(){
		this.emptySpots--;
	}

	public String toString(){
		return "Solution: bufferSize -> " + this.getBufferSize()
			+ " \nNumber of Producers -> " + this.getNumProducers()
			+ " \nNUmber of Consumers -> " + this.getNumConsumers();
	}

}