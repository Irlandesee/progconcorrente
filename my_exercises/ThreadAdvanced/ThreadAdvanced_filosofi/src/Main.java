import solution.*;


public class Main{

	private static final int NUM_PHIL = 5;

	public static void main(String[] args){
		ChopStick[] sticks = new ChopStick[NUM_PHIL];
		for(int i = 0; i < NUM_PHIL; i++)
			sticks[i] = new ChopStick(i+1);
		Waiter w = new Waiter();
		for(int i = 0; i < NUM_PHIL; i++)
			new Phil("F"+(i+1), sticks[i], sticks[(i+1)%NUM_PHIL], w).start();
	}

}