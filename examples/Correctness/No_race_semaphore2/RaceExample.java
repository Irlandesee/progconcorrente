public class RaceExample extends Thread {
	private Counter myCounter;
	public RaceExample(Counter c){
		myCounter=c;
	}
	public void run() {
		for(int i=0; i<10000; i++) {
			myCounter.lock();
			myCounter.add(1);
			myCounter.unlock();
		}
	}
	public static void main(String args[])
			throws InterruptedException {
		Counter counter = new Counter();
		RaceExample p1 = new RaceExample(counter);
		RaceExample p2 = new RaceExample(counter);
		p1.start();  p2.start();
		p1.join(); p2.join();
		System.out.println("Counter = " + counter.getValue());
	}
}
