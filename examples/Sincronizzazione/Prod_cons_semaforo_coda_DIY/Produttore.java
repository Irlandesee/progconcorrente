public class Produttore extends Thread {
	CodaCondivisa cella;
	public Produttore(String s, CodaCondivisa c){
		super(s);
		this.cella=c;
	}
	public void run(){
		int i=0;
		for(;;){
			try{
				ProdCons.empty.acquire();
			} catch(InterruptedException e) {}
			cella.setItem(i++);
			ProdCons.full.release();
		}
	}
}


