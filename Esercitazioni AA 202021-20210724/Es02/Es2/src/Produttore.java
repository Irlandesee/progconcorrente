
public class Produttore extends Thread {
	private CellaCondivisaCoda cella;
	private int myId;
	public Produttore(CellaCondivisaCoda c, int id){
		this.cella=c;
		this.myId=id;
	}
	public void run(){
		int i=0;
		for(;;){
			cella.setItem(i++);
			System.out.println("Producer "+myId+" wrote");
		}
	}
}


