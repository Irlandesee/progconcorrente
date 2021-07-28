
public interface SignalWaiter {
	void waits() throws InterruptedException;
	boolean waits(long t) throws InterruptedException;
}
