
public class DiskController {
	private PersistentSignal reply;

	// Various operations including:

	public SignalWaiterOrWatcher asyncWrite(int blockNumber, int from) {
		reply=new PersistentSignal();
		new DiskWriter(reply).start();	// Set up the write operation
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {	}
		return reply;
	}
}

