import java.util.concurrent.ThreadLocalRandom;

class Consumatore extends Thread{
	CellaCondivisa cella;
	public Consumatore(CellaCondivisa c){
		this.cella=c;
	}
	public void run(){
		int v;
		for(int i=1; i<=10; ++i){
			synchronized(cella){
				v=cella.getValore();
				System.out.print(" C"+i+"("+v+")");
			}  // end synchronized
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(10, 100));
			} catch (InterruptedException e) {	}
		}
	}
}

