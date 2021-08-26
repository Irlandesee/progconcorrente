package solution;

public class Waiter{

	public synchronized void takeTwo(ChopStick i, ChopStick j){
		while(!(i.isAvailable() && j.isAvailable())){
			try{
				wait();
			}catch(InterruptedException e){ }
		}
		i.take();
		j.take();
	}

	public synchronized void leaveTwo(ChopStick i, ChopStick j){
		i.leave();
		j.leave();
		notifyAll();
	}

}