package solution;

import java.util.Random;

public class Producer{

	private int bound; 
	private Random rand;
	private int[] a;

	public Producer(int bound, int[] a){
		this.bound = bound;
		this.a = a;
		rand = new Random(bound);
	}

	private int next(){
		return rand.nextInt();
	}

	public boolean insert(){
		int item = next();
		System.out.println(toString() + item);
		
	}

	public String toString(){
		return "Producer : ";
	}
}