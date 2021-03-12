public class Produttore extends Thread {
	CellaCondivisa cella;
	public Produttore(String s, CellaCondivisa c){
		super(s);
		this.cella=c;
	}
	public void run(){
		int i=0;
		for(;;){
			try{
				ProdCons.empty.acquire();
			} catch(InterruptedException e) {}
			cella.setValore(i++);
			ProdCons.full.release();
		}
	}
}


