public class Consumatore extends Thread {
	CellaCondivisaCoda cella;
	int v;
	public Consumatore(CellaCondivisaCoda c){
		this.cella=c;
	}
	public void run(){
		for(;;){
			v=cella.getItem();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) { }
		}
	}
}

