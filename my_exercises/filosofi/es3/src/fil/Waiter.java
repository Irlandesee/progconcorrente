package fil;

public class Waiter{

	private Pool p;

	public Waiter(Pool _p){
		p = _p;
		this.start();
	}	

	public void run(){
		while(true){
			p.scheduleNext();
		}
	}

}