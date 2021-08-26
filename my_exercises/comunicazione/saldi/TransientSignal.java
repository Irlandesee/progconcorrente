public class TransientSignal extends Signal{
	protected int waiting = 0;
	public synchronized void send(){
		if(waiting > 0)
			super.send();
	}

	public synchronized void waits() throws InterruptedException{
		try{
			waiting++;
			while(!arrived)
				wait();
			waiting--;
			arrived = false;
		}catch(InterruptedException e){
			waiting--;
			throw e;
		}
	}
}