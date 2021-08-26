package solution;

public class ChopStick{

	public enum State {AVAILABLE, BUSY}
	private State state;
	private int id;
	public ChopStick(int id){
		this.id = id;
		this.state = ChopStick.State.AVAILABLE;
	}

	public void take(){
		this.state = ChopStick.State.BUSY;
	}

	public void leave(){
		this.state = ChopStick.State.AVAILABLE;
	}

	public boolean isAvailable(){
		return (this.state == ChopStick.State.AVAILABLE);
	}

	public String getName(){
		return "f"+id;
	}

	public int getId(){
		return this.id;
	}

}
