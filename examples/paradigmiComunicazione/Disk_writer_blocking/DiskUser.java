
public class DiskUser {
	public static void main(String[] args) {
		DiskController controller = new DiskController();
		SignalWaiterOrWatcher outputDone;
		System.out.println("Starting Write operation");
		outputDone = controller.asyncWrite(0,199);
		long call_time=System.currentTimeMillis();
		// . . . .
		// When it is time to check that the output is complete:
		try {
			outputDone.waits();
			System.out.println("Write completed after "+(System.currentTimeMillis()-call_time)+" ms");
		} catch (InterruptedException ie ) {
			// Initiate recovery action.
		}
	}
}
