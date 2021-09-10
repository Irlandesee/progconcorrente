package fil;

public class Chopstick {

	public enum ChopState {AVAILABLE, BUSY}
	private ChopState state;
	private int id;

	public Chopstick(int id) {
		this.id = id;
		this.state=Chopstick.ChopState.AVAILABLE;
	}

	public boolean isAvaliable() {
		return state==Chopstick.ChopState.AVAILABLE;
	}
	
	public void take( ) {
		this.state=Chopstick.ChopState.BUSY;
	}
	
	public void leave() {
		this.state=Chopstick.ChopState.AVAILABLE;
	}
	
	public String getName() {
		return "f"+id;
	}
	
	public int getId() {
		return this.id;
	}
}
