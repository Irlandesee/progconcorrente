public class Consumatore extends Thread {
	private CellaCondivisaCoda cella;
	int v, myId;
	public Consumatore(CellaCondivisaCoda c, int id){
		this.cella=c;
		this.myId=id;
	}
	public void run(){
		for(;;){
			v=cella.getItem();
			System.out.println("Consumer "+myId+" read");
		}
	}
}

