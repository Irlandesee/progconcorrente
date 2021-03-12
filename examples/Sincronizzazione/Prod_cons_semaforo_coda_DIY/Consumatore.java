public class Consumatore extends Thread {
	CodaCondivisa cella;
	int v;
	public Consumatore(String s, CodaCondivisa c){
		super(s);
		this.cella=c;
	}
	public void run(){
		for(;;){
			try{
				ProdCons.full.acquire();
			} catch(InterruptedException e) {}
			v=cella.getItem();
			ProdCons.empty.release();
		}
	}
}

