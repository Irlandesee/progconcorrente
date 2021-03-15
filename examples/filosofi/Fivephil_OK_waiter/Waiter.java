public class Waiter {
	public synchronized void takeTwo(Chopstick i, Chopstick j) {
		while(!(i.isAvailable() && j.isAvailable())) {
			try {
				wait() ;
			} catch (InterruptedException e) { }
		}
		i.take();
		j.take();
	}
	public synchronized void leaveTwo(Chopstick i, Chopstick j) {
		i.leave();
		j.leave();
		notifyAll();
	}
}

