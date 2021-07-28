
public class PersistentSignal extends Signal
implements SignalWaiterOrWatcher {
	public synchronized void waits()
			throws InterruptedException {
		while(!arrived)
			wait(); // Wait for a new signal
		arrived = false;
	}

	public synchronized boolean watch() {
		// This method never waits
		if(!arrived)
			return false;
		arrived = false;
		return true;
	}
}
