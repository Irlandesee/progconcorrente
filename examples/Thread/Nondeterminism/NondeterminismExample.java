public class NondeterminismExample extends Thread{
	final static int numIterations=10;
	public void run() {
		for(int i=0; i<numIterations; i++) {
			System.out.println("Nuovo thread");
		}
	}
	public static void main(String args []) {
		NondeterminismExample t=new NondeterminismExample();
		t.start();
		for(int i=0; i<numIterations; i++) {
			System.out.println("Main");
		}
	}
}
