
public class Produttore extends Thread {
	CellaCondivisaCoda cella;
	public Produttore(CellaCondivisaCoda c){
		this.cella=c;
	}
	public void run(){
		int i=0;
		for(;;){
			cella.setItem(i++);
		}
	}
}


