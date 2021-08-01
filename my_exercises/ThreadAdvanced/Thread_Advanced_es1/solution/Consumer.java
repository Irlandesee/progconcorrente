package solution;

public class Consumer extends Thread{

	private Solution s;

	public Consumer(Solution s){
		this.s = s;
		this.start();
	}

	public void run(){
		
	}

	/**
	 * Checks whether the posion is "closed", if so
	 * removes the item at a[index] and opens the posion
	 * by inserting -1; otherwise if the position is already
	 * open returns the array. 
	 * Not Thread Safe!
	 * */
	public int[] remove(int index, int[] a){
		if(a[index] < 0)
			System.out.println("Position is already open!");
		else{
			int itemRemoved = a[index];
			a[index] = -1;
			System.out.printf("item: %d, removed at -> %d\n", itemRemoved, index);
		}
		return a;
	}

	public void printPositions(int[] a){
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

	public String toString(){
		return "Consumer: ";
	}

}