import java.util.concurrent.*;

public class Consumatore extends Thread{

	BlockingQueue<String> q;
	int id;
	String v;

	public Consumatore (int n, BlockingQueue q){
		id = n;
		this.q = q;

	}


	private void dormi(){
		try{
			Thread.sleep(ThreadLocalRandom.current().nextInt(1, 40));
		}catch(InterruptedException e){}
	}

	public void run(){

		for(;;){
			boolean done = false;

			while(!done){
				v = q.poll();
				if(v != null)
					done = true;
				else{
					System.out.println("Consumatore dorme");
					dormi();
				}
			}
			System.out.println("Consumato: "+v);
			try{
				Thread.sleep(100);
			}catch(InterruptedException e){}
		}

	}

}