package fil;

public static final int NUM_PHIL = 5;
public static final int NUM_STICKS = 5;

public class Table{

	public static int getNumberPhils(){
		return NUM_PHIL;
	}

	public static int getNumberSticks(){
		return NUM_STICKS;
	}

	public static void main(String[] args){
		Pool p = new Pool(NUM_PHIL, NUM_STICKS);
		Waiter w = new Waiter(p);
		for(int i = 0; i < NUM_PHIL; i++)
			new Philosopher(i, p).start(); 
	}


}