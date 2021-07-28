
public class Broadcast<Data> {
	private Data theMessage;
	private boolean arrived;
	private int waiting;
	public Broadcast() {
		arrived = false ;
		waiting = 0;
	}
	// per inviare un messaggio in broadcast
	public synchronized void send(Data message) {
		if(waiting!=0 && !arrived) {
			theMessage = message;
			arrived = true;
			notifyAll();
		}
	}

	//per mettersi in attesa di un messaggio
	public synchronized Data receive() throws InterruptedException {
		try {
			waiting++;
			while(!arrived){ // wait for a message to arrive
				wait();
			}
			waiting--;
			if(waiting==0) {
				// The last thread to receive the message resets the flag
				arrived = false ;
			}
		} catch (InterruptedException ie) {
			if (--waiting == 0)
				arrived = false;
		}
		return theMessage;
	}
}

