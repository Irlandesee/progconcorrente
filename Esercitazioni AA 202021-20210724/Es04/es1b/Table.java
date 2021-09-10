
public class Table {
	private static final int NUM_PHIL = 5;
	private static final int NUM_STICKS = 5;
	private static Pool thePool= new Pool(NUM_STICKS);
	public static void main(String[] args) {
		for(int i=0; i<NUM_PHIL; i++)
			new Philosopher("F"+(i+1), thePool).start();
	}
}
