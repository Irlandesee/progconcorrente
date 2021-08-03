package solution;
import java.util.concurrent.ThreadLocalRandom;

public class Producer extends Thread{

	private final String name;
	private Solution s;

	private boolean runCondition;

	public Producer(String name, Solution s){
		this.name = name;
		this.s = s;
		runCondition = true;
		this.start();
	}	

	public void run(){
		while(runCondition){
			try{
				Thread.sleep(ThreadLocalRandom.current().nextInt(10, 100));
			}catch(InterruptedException e){ }
			synchronized(s){
				int value = (int) (100*Math.random());
				System.out.printf("Producer: %s, prodotto %d\n", this.toString(), value);
				s.insert(value);
			}
		}
	}

	public String toString(){
		return this.name;
	}

}