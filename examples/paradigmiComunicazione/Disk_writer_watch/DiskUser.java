
public class DiskUser {
	public static void main(String[] args) {
		DiskController controller = new DiskController();
		SignalWaiterOrWatcher outputDone;
		System.out.println("Starting Write operation");
		outputDone = controller.asyncWrite(0,199);
		long call_time=System.currentTimeMillis();
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) { }
		// It is time to check that the output is complete:
		if(outputDone.watch()){
			System.out.println("Write completed after "+(System.currentTimeMillis()-call_time)+" ms");
		} else {
			System.out.println("Recovery action started");
		}
	}
}
