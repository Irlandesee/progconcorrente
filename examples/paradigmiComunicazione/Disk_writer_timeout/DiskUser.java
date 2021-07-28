
public class DiskUser {
	public static void main(String[] args) {
		DiskController controller = new DiskController();
		SignalWaiterOrWatcher outputDone;
		System.out.println("Starting Write operation");
		outputDone = controller.asyncWrite(0,199);
		// . . . .
		// When it is time to check that the output is complete:
		try {
			if(outputDone.waits((long) 345)) {
				System.out.println("Write completed");
			} else {
				System.out.println("Write not completed: attempting recovery");
			}
		} catch (InterruptedException ie ) {
			// Initiate recovery action.
			System.out.println("Performing recovery");
		}
	}
}
