public class Chopstick {
	public enum State {AVAILABLE, BUSY}
	private State state;
	private int id;
	public Chopstick(int id) {
		this.id = id;
		this.state=Chopstick.State.AVAILABLE;
	}
	public synchronized void leave() {
		this.state=Chopstick.State.AVAILABLE;
		notify();
	}
	public String getName() {
		return "f"+id;
	}
	public int getId() {
		return this.id;
	}
	public synchronized boolean take(long t)
			throws InterruptedException {
		if(t==0){
			while(state==Chopstick.State.BUSY) {
				wait();
			}
			this.state=Chopstick.State.BUSY;
			return (true);	
		} else {
			while(state==Chopstick.State.BUSY) {
				wait(t);
				if(state!=Chopstick.State.BUSY){
					break;	
				} else {
					notifyAll();
					return (false);
				}
			}
			this.state=Chopstick.State.BUSY;
			return (true);
		}
	}
}



