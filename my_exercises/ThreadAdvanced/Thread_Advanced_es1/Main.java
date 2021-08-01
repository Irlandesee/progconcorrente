import java.util.Random;

public class Main{


	/**
	 * if the pos at a[index] is "open"/"free" then the 
	 * item is insert, otherwise the array is returned 
	 * a[index] == -1 -> pos is open
	 * a[index] == 5 -> pos is closed
	 * */
	public int[] insert(int index, int item, int[] a){
		if(a[index] == -1){
			a[index] = item;
			System.out.printf("Item: %d, inserted at -> %d\n", item, index);
		}
		else
			System.out.println("Position is closed!");
		return a;
	}

	/**
	 * Checks whether the posion is "closed", if so
	 * removes the item at a[index] and opens the posion
	 * by inserting -1; otherwise if the position is already
	 * open returns the array. 
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

	public static void main(String[] args){
		int boundNumbers = 100;
		int boundPositions = 10;
		int arraySize = 10;
		Random rand = new Random();

		int[] a = new int[arraySize];

		//preparing array
		for(int i = 0; i < a.length; i++)
			a[i] = -1;

		Main m = new Main();

		// m.printPositions(a);

		int randomPosition = rand.nextInt(boundPositions);
		int randomItem = rand.nextInt(boundNumbers);
		m.insert(randomPosition, randomItem, a);

		m.printPositions(a);

		m.remove(randomPosition, a);
		
		m.printPositions(a);

	}


}