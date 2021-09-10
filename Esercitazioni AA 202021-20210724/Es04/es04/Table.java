
public class Table {
	public static final int NUM_PHIL = 5;
	Philosopher[] phils=new Philosopher[NUM_PHIL];
	Chopstick[] sticks=new Chopstick[NUM_PHIL];
	private void exec() {
		for(int i=0; i<NUM_PHIL; i++) {
			sticks[i]=new Chopstick(i);
		}
		System.out.println("Chopsticks created");
		for(int i=0; i<NUM_PHIL; i++) {
			int left=i-1;
			if(left<0) { left=NUM_PHIL-1; }
			int right=(i+1)%NUM_PHIL;
			System.out.println("Creating phil with "+left+" "+right);
			phils[i]=new Philosopher(i, sticks[left], sticks[right]);
		}
		System.out.println("Philosophers created");
		for(int i=0; i<NUM_PHIL; i++) {
			sticks[i].setOwner((i-1)<0?(i+1):(i-1));
		}
		System.out.println("Chopstick owners set");
		for(int i=0; i<NUM_PHIL; i++) {
			phils[i].start();
		}
	}
	public static void main(String[] args) {
		Table t = new Table();
		t.exec();
	}
}