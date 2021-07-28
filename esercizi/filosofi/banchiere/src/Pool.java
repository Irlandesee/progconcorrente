public class Pool{

	private int size;
	private Chopstick[] sticks;

	public Pool(int numStick){
		this.size = numStick;
		sticks = new Chopstick[numSticks];
		for(int i = 0; i < numSticks; i++)
			sticks[i] = new Chopstick(i+1);
	}

}