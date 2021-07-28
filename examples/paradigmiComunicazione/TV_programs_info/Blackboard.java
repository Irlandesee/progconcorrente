public class Blackboard<Data> {
	private Data theMessage;
	private boolean statusValid;
	public Blackboard () {
		statusValid = false ;
	}
	public Blackboard(Data initial) {
		theMessage = initial;
		statusValid = true;
	}
	public synchronized void write(Data message) {
		theMessage = message ;
		statusValid = true ;
		notifyAll();
	}
	public synchronized void clear(){
		statusValid = false ;
	}
	public synchronized Data read() throws InterruptedException {
		while(!statusValid)
			wait();
		return theMessage;
	}
	public boolean dataAvailable() {
		return statusValid ;
	}
}
