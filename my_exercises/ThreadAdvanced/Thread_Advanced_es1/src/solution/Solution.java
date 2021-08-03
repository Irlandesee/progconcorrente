package solution;
import java.util.concurrent.ThreadLocalRandom;

public class Solution{

	private int[] buffer; //bounded buffer

	private final int capacity;
	private int currentCapacity;

	private int currentIndex;

	public Solution(int capacity){
		this.capacity = capacity;
		currentCapacity = capacity;
		buffer = new int[capacity];
		currentIndex = 0;
	}
	
	public synchronized void insert(int item){
		if(currentCapacity == 0){
			try{ wait(); }catch(InterruptedException e){ }
		}
		else{
			int previousItem = buffer[currentIndex];
			buffer[currentIndex] = item;
			//System.out.printf("Item %d -> \n %d : %d",
				//previousItem, currentIndex, item);
			currentIndex++;
			currentCapacity++;
			if(currentIndex == capacity){
				print();
				currentIndex = 0;
			}
		}
		notify();
	}

	public synchronized int remove(){
		int deletedItem = 0;
		if(currentCapacity == capacity){
			try{ wait(); } catch(InterruptedException e) { }
		}
		else{
			deletedItem = buffer[currentIndex];
			//System.out.printf("Item deleted %d at %d",
				//deletedItem, currentIndex);
			currentIndex++;
			currentCapacity++;
			if(currentIndex == capacity){
				print();
				currentIndex = 0;
			}
		}
		notify();
		return deletedItem;
	}

	public void print(){
		synchronized(this){
			for(int i = 0; i < buffer.length; i++){
				if(i == buffer.length-1)
					System.out.printf("%d\n", buffer[i]);
				else
					System.out.printf("%d ", buffer[i]);
			}
		}
	}

	public int getCurrentCapacity(){
		return this.currentCapacity;
	}
	
	public int getCapacity(){
		return this.capacity;
	}

}