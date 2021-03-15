public class Chopstick {
	public enum State {AVAILABLE, BUSY}
	private State state;
	private int id;
	public Chopstick(int id) {
		this.id = id;
		this.state=Chopstick.State.AVAILABLE;
	}
	public void take( ) {
		this.state=Chopstick.State.BUSY;
	}
	public boolean isAvailable( ) {
		return (this.state==Chopstick.State.AVAILABLE);
	}
	public void leave() {
		this.state=Chopstick.State.AVAILABLE;
	}
	public String getName() { return "f"+id; }
	public int getId() { return this.id; }
}
