import java.util.concurrent.*;

public class Produttore extends Thread{

	BlockingQueue<String> q;
	int id;
	String v;

	public Produttore(int n, BlockingQueue<String> q){
		this.q = q;
		id = n;
	}

	private void dormi(){
		try{
			Thread.sleep(ThreadLocalRandom.current().nextInt(1, 40)); //durata casuale
		}catch(InterruptedException e){}
	}

	public void run(){
		for(;;){
			try{
				Thread.sleep(100);//simula tempo necessario a "produrre" la stringa v
			}catch(InterruptedException e){}
			v = "str_"+id+"_"+id;
			System.out.println("prodotto v");
			boolean done  = false;
			while(!done){	//fintanto che non riesco ad inserire, dormo.
				try{
					done = q.add(v);
				}catch(IllegalStateException e){
					System.out.println("Produttore dorme");
					dormi();
					done = false;
				}
			}


		}
	}

}
