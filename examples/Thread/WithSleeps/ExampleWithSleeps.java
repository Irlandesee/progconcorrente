public class ExampleWithSleeps extends Thread{
	final static int numIterations=10;
	public void run() {
		for(int i=0; i<numIterations; i++) {
			System.out.println("Nuovo thread");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {	}
		}
	}
	public static void main(String args []) {
		ExampleWithSleeps t=new ExampleWithSleeps();
		t.start();
		for(int i=0; i<numIterations; i++) {
			System.out.println("Main");
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {	}
		}
	}
}
