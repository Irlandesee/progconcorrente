# paradigmi di comunicazione

* Modi con cui i thread possono comunicare tra loro:
	- Signals
	- Buffer
	- Blackboard
	- Broadcast
	- Barrier

# Signal

* Consente ad un thread di attendere un segnale inviato da un altro thread

- 2 tipi di segnali:
	* Persistent signal: è segnale che rimasto impostato fino a quando un singolo thread non lo riceve.

	* Transient signal: segnale che rilascia uno o più thread in attesa, ma si perde se non ci sono thread in attesa.

* 2 ruoli distinti:
	- Chi aspetta il segnale
	- Chi invia il segnale atteso.

	-> Viene separata l'interfaccia del thrad che invia da quello che aspetta, così facendo ogni thread può chiamare l'operazione più appropriata.

# example

public interface SignalSender{

	void send();

}

public interface SignalWaiter{
	void waits() throws InterruptedException;
}

- Definisco una classe astratta dalla quale si possono derivare classi concrete che realizzano i segnali persistenti e transienti

public abstract class Signal implements SignalSender, SignalWaiter{

	protected boolean arrived = false;
	public abstract void waits() throws Interrupted Exception;
	public synchronized void send(){
		arrived = true;
		notify();
	}

}

public interface SignalWaiterOrWatcher extends SignalWaiter{

	boolean watch();

}

public class PersistentSignal extends Signal implements SignalWaiterOrWatcher{

	public synchronized void waits() throws InterruptedException{
		while(!arrived)
			wait();
		arrived = false;
	}

	public synchronized boolean watch(){
		//this method never waits
		if(!arrived) //polling
			return false;
		arrived = false;
		return true;
	}

}


# esempio di persistent dignal: accesso al disco

# soluzione 1

public class DiskController {

	private PersistentSignal reply;

	//various operations including:

	public SignalWaiterOrWatcher asyncWrite(int blockNumber, Block from){
		reply = new PersistentSignal();
		//set up write operation then:
		return reply; // fa return prima di aver concluso la scrittura, è il 
					// chiamante che deve verificare se la scrittura è andata
					// a buon fine
	}

	public static void main(String[] args){
		DiskController controller = new DiskController();
		Block superBlock = new Block();
		SignalWaiterOrWatcher outputDone;
		outputDone = controller.asyncWrite(0, superBlock);
		//..
		//when it is time to check that the output is complete
		try{
			outputDone.waits(); //aspetta che l'operazioen di scrittura sia
								//terminata o che succeda qualcosa (interrupt)
		}
		catch(InterruptedException ie){
			//initate recovery action
		}
	}
}

# soluzione 2

public static void main(String[] args){
	DiskController controller = new DiskController();
	Block superBlock = new Block();
	SignalWaiterOrWatcher outputDone;
	outputDone = controller.asyncWrite(0, superBlock);
	//...
	//when it is time to check that the output is complete:
	if(!outputDOne.watch()){
		//output not complete, initiate recovery action. 
		//-> Non aspetta ulteriormente che l'operazione di scrittura sia
		//terminata, inizia subito la recovery.
	}
} 

# Gestione delle attese

public interface SignalWaiter{
	void waits() throws InterruptedException;
	boolean waits(long t) throws InterrptedException; //waits con timeout
}

public class PersistentSignal extends Signal implements SignalWaiterOrWatcher{
	public synchronized boolean  waits(long t) throws InterruptedException{
		long call_time = System.currentTimeMillis(), elapsed = 0;
		while(!arrived && elapsed < t){
			wait(t - elapsed) //wait for signal
			if(arrived){
				arrived = false;
				return true;
			}
			else{
				elapsed = System.currentTimeMillis() - call_time;
			}
		}
	}
	return false;
}

# soluzione 3

public static void main (String[] args){
	DiskController controller = new DiskController();
	Block superBlock = new Block;
	SignalWaiterOrWatcher outputDone;
	System.out.println("Starting write operation");
	outputDone = controller.asyncWrite(0, superBlock);
	//...
	//when it is time to check that the output is complete:
	try{
		if(outputDone.waits((long) 345)){
			System.out.println("Write completed");
		}else{
			System.out.println("Write not completed");
		}
	} catch(InterruptedException ie){
		//initiate recovery action.
	}
}

# Transient Signal

public class TransientSignal extends Signal{ //no watcher
	
	protected int waiting = 0;
	
	public synchronized void send(){
		if(waiting > 0)
			super.send(); //sveglia solo un thread
	}

	public synchronized void waits() throws InterruptedException{
		try{
			waiting++;
			while(!arrived)
				wait();
			waiting--;
			arrived = false;
		} catch(InterruptedException e){
			waiting--;
			throw ie;
		}

	}

}

# class Pulse
- Rilascia tutti i thread in waitForSignal

public class Pulse extends TransientSignal{
	//sveglia tutti i thread in attesa del segnale.
	public synchronized void sendAll(){
		if(waiting > 0 ){
			arrived = true;
			notifyAll();
		}
	}
	
	public synchronized void waits() throws InterruptedException{
		//Overrides inherited waits
		try{
			waiting++;
			while(!arrived)
				wait();
			waiting--;
			if(waiting == 0)
				arrived = false; //solo l'ultimo thread svegliato resetta 
								// il flag
		} catch(InterruptedException ie){
			if(--waiting == 0)
				arrived = false;
			throw ie;
		}
	}

}
