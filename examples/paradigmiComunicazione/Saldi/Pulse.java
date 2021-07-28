public class Pulse extends TransientSignal  {
	private boolean arrived;
	private int waiting;
	public Pulse() {
		this.arrived=false;
		this.waiting=0;	
	}
	public synchronized void waits() throws InterruptedException {
		try {
			waiting++;
			while(!arrived)
				wait();
			waiting--;
			if(waiting<=0)
				arrived=false;
		} catch(InterruptedException ie) {
			waiting--;
			throw ie;
		}
	}
	public synchronized void sendAll() {
		if(waiting>0) {
			arrived=true;
			notifyAll();
		}
	}
}
