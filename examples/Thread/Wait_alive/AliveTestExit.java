
public class AliveTestExit {
	public static void main(String args[]) throws Exception {
		System.err.println("I thread stanno per partire");
		Thread t1 = new ExampleWithSleeps("t1"); t1.start();
		Thread t2 = new ExampleWithSleeps("t2"); t2.start();
		Thread t3 = new ExampleWithSleeps("t3"); t3.start();
		System.err.println("I thread sono partiti\n");
		while (t1.isAlive() || t2.isAlive() || t3.isAlive()){
			Thread.sleep(100);
		}
		System.err.println("chiude l'applicazione");
		System.exit(0); //chiude l'applicazione in ogni caso
	}
}
