public class Chopstick{

	public enum State {AVAILABLE, BUSY}
	private State state;
	private int id;
	public Chopstick(int id){
		this.id = id;
		this.state = Chopstick.State.AVAILABLE;
	}

	public boolean isAvailable(){
		return state == Chopstick.State.AVAILABLE;
	}

	


}