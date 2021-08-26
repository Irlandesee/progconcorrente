public class Pulse extends TransientSignal{

	public synchronized void sendAll(){
		if(waiting > 0){
			arrived = true;
			notifyAll();
		}
	}

	public synchronized void waits() throws InterruptedException{
		try{
			waiting++;
			while(!arrived)
				wait();
			waiting--;
			if(waiting == 0)
				arrived = false;
		}catch(InterruptedException e){
			if(--waiting == 0)
				arrived = false;
			throw e;
		}
	}
}