public class Consumatore extends Thread {
	CellaCondivisa cella;
	int v;
	public Consumatore(String s, CellaCondivisa c){
		super(s);
		this.cella=c;
	}
	public void run(){
		for(;;){
			try{
				ProdCons.full.acquire();
			} catch(InterruptedException e) {}
			v=cella.getValore();
			ProdCons.empty.release();
		}
	}
}

