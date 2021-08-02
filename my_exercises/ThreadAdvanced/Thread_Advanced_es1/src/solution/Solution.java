
public class Solution{

	private int[] buffer;

	private final int capacity;
	private int currentCapacity;

	public Solution(int capacity){
		this.capacity = capacity;
		currentCapacity = capacity;
		buffer = new int[capacity];
	}
	
	public void insert(int item){
		
	}

	public synchronized int remove(){
		if(currentCapacity == 0){
			wait();
		} catch(InterruptedException e){ }

	}

	public void print(){

	}

	public int getCurrentCapacity(){
		return this.currentCapacity;
	}
	
	public int getCapacity(){
		return this.capacity;
	}

	public String toString(){

	}

}