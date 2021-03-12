public class ExampleWithSleeps extends Thread{
	final static int numIterations=10;
	public ExampleWithSleeps(String s) {
		super(s);
	}
	public void run() {
		for(int i=0; i<numIterations; i++) {
			System.out.println("thread "+getName()+" in esec.");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				System.err.println(getName() + ": interrotto");
				break;
			}
		}
		System.out.println("thread "+getName()+": finito.");
	}
}
