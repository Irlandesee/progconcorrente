public class Consumatore extends Thread {
	CellaCondivisa cella;
	int v;
	public Consumatore(String name, CellaCondivisa c){
		super(name);
		this.cella=c;
	}
	public void run(){
		for(;;){
			v=cella.getValore();
		}
	}
}



