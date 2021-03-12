import java.util.concurrent.ThreadLocalRandom;

class Consumatore extends Thread{
	CellaCondivisa cella;
	public Consumatore(CellaCondivisa c){
		this.cella=c;
	}
	public void run(){
		int v;
		for(int i=1; i<=10; ++i){
			v=cella.getValore();
		}
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(10, 100));
		} catch (InterruptedException e) {	}
	}
}


