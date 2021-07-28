
public class PersistentSignal extends Signal implements SignalWaiterOrWatcher {
	public synchronized void waits()
			throws InterruptedException {
		while(!arrived)
			wait(); // Wait for a new signal
		arrived = false;
	}

	public synchronized boolean waits(long t) throws InterruptedException {
		long call_time=System.currentTimeMillis(), elapsed=0;
		while(!arrived && elapsed < t){
			wait(t-elapsed); // Wait for signal
			if(arrived) {
				arrived = false;
				return true;
			} else {
				elapsed=System.currentTimeMillis()-call_time;
			}
		}
		return false;
	}

	public synchronized boolean watch() {
		// This method never waits
		if(!arrived)
			return false;
		arrived = false;
		return true;
	}
}
