public class Barrier {
	private int needed, arrived, numCycles;
	private boolean releasing;
	public Barrier(int number) {
		needed=number; // Number of threads to block
		arrived=0;
		numCycles=0;
		releasing=false;
	}
	public synchronized void waitB() throws InterruptedException {
		while(releasing)
			wait();
		// Wait for previous batch to depart
		try {
			arrived++;
			while(arrived!=needed && !releasing)
				wait();
			if(arrived==needed) { // When all present
				releasing=true;
				System.out.println("All "+needed+" parties arrived at barrier");
				numCycles++;
				notifyAll();
			}
		} finally {
			arrived--;
			if(arrived==0) { // Last thread to leave
				releasing=false;
				notifyAll(); // Allow new batch to arrive 
			}
		} 
	}
	public synchronized int value () {
		return arrived ;
	}
	public synchronized int waitCycle(int cycleNum) throws InterruptedException {
		if(cycleNum!=numCycles)
			wait();
		return numCycles;
	}
	public int capacity() {
		return needed;
	}
}