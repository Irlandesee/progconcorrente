package fil;

public class Chopstick{

	public enum Chopstate {AVAILABLE, BUSY}
	private Chopstate state;
	private int id;

	public Chopstick(int _id){
		id = _id;
		state = Chopstick.Chopstate.AVAILABLE;
	}

	public boolean isAvaliable(){
		return state == Chopstick.Chopstate.AVAILABLE;
	}

	public void take(){
		this.state = Chopstick.Chopstate.BUSY;
	}

	public void leave(){
		this.state = Chopstick.Chopstate.AVAILABLE;
	}

	public String getName(){
		return "f"+id;
	}

	public int getID(){
		return this.id;
	}



}