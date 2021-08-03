package solution;
import java.util.concurrent.ThreadLocalRandom;

public class Consumer extends Thread{

	private final String name;
	private Solution s;

	private boolean runCondition;

	public Consumer(String name, Solution s){
		this.name = name;
		this.s = s;

		runCondition = true;
		this.start();

	}

	public void run(){
		int valoreConsumato;
		while(runCondition){
			synchronized(s){
				valoreConsumato = s.remove();
				System.out.printf("Consumer: %s, consumato: %d\n", this.toString(), valoreConsumato);
			}
			try{
				Thread.sleep(ThreadLocalRandom.current().nextInt(10, 100));
			}
			catch(InterruptedException e){ }
		}
	}

	public String toString(){
		return this.name;
	}

}