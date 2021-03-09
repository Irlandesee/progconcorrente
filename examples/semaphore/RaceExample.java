import java.util.concurrent.Semaphore;
public class RaceExample extends Thread{

	private Counter myCounter;
	private Semaphore s;

	public RaceExample(Counter c, Semaphore s){
		myCounter = c;
		this.s = s;
	}

	public void run(){
		for(int i = 0; i < 10000; i++){
			try{
				System.out.println(myCounter.toString());
				s.acquire();
			}catch(InterruptedException e){
				myCounter.add();
				s.release();
			}
		}
	}

}