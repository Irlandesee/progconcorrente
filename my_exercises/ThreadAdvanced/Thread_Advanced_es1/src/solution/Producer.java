package solution;

import java.util.Random;
import java.util.ThreadLocalRandom; // USARE QUESTA PER LA GENERAZIONE DI NUMERI
									//RANDOMICI

public class Producer extends Thread{

	private int boundNumbers; 
	private int boundIndex;
	private Random rand; // con piu produttori forse è possibile r.c su rand
	private Solution s;
	private boolean runCondition;

	public Producer(int boundNumbers, Solution s){
		this.boundNumbers = boundNumbers;
		rand = new Random();
		this.s = s;
		runCondition = true; //at creation runCondition is always true
		this.start();
	}

	public void run(){
		while(runCondition){ //r.c. on runCondition (?)
			
			int item = produceRandomItem();
			int index = produceRandomIndex();

			//start of cs
			int[] bufferCopy = getBufferCopy();

			//inserting item in the buffer
			bufferCopy = insert(index, item, bufferCopy);
			//copying the new buffer in the old buffer
			setBufferCopy(bufferCopy);
			//end of cs

			runCondition = getRunCondition(); //checks if it still needs to run
			printPositions();
		}
	}

	private synchronized int[] getBufferCopy(){
		return s.getBuffer();
	}

	private synchronized void setBufferCopy(int[] a){
		s.setBuffer(a);
	}

	/**
	 * if the pos at a[index] is "open"/"free" then the 
	 * item is insert, otherwise the array is returned 
	 * a[index] == -1 -> pos is open
	 * a[index] == 5 -> pos is closed
	 * Not Thread Safe
	 * */
	private synchronized int[] insert(int index, int item, int[] a){
		if(a[index] == -1){
			a[index] = item;
			System.out.printf("Item: %d, inserted at -> %d\n", item, index);
		}
		else
			System.out.println("Position is closed!");
		return a;
	}

	public synchronized void printPositions(int[] a){
		String closed = "closed";
		String open = "open";
		System.out.println("[PRINTING POSITIONS]");
		for(int i = 0; i < a.length; i++){
			if(a[i] < 0) //position is open
				System.out.printf("index: %d -> %s\n", i, open);
			else
				System.out.printf("index: %d -> value: %d, %s\n", i, a[i], closed);
		}
		System.out.println("[DONE]");
	}

	//Not Thread Safe (?)
	private int produceRandomIndex(){
		return rand.nextInt(boundIndex);
	}

	//Not Thread Safe (?)
	private int produceRandomItem(){
		return rand.nextInt(boundNumbers);
	}

	public int getBoundNumbers(){
		return this.boundNumbers;
	}

	public int getBoundIndex(){
		return this.boundIndex;
	}

	public boolean getRunCondition(){
		return this.runCondition;
	}

	public void setRunCondition(boolean runCondition){
		this.runCondition = runCondition;
	}

	public String toString(){
		return "Producer: "
		+ "\nboundNumbers -> " + this.getBoundNumbers()
		+ "\nbound index -> " + this.getBoundIndex();
	}

}