import java.util.concurrent.Semaphore;
public class Main{

	public static void main(String[] args) throws InterruptedException{
		Counter c = new Counter(1);
		Semaphore sem = new Semaphore(1);

		RaceExample p1 = new RaceExample(c, sem);
		RaceExample p2 = new RaceExample(c ,sem);

		p1.start();
		p2.start();

		p1.join();
		p2.join();

		System.out.println(c.toString());

	}

}