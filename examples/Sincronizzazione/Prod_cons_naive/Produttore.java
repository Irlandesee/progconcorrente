import java.util.concurrent.ThreadLocalRandom;

class Produttore extends Thread{
	CellaCondivisa cella;
	public Produttore(CellaCondivisa c){
		this.cella=c;
	}
	public void run(){
		for(int i=1; i<=10; ++i){
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(10, 100));
			} catch (InterruptedException e) {	}
			synchronized(cella){
				int v=(int)(100*Math.random());
				cella.setValore(v);
				System.out.print(" P"+i+"("+v+")");
			}  // end synchronized
		}
	}
}
