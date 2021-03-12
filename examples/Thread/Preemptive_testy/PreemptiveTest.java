
public class PreemptiveTest {
	public static void main(String args []) {
		System.out.println("Main: inizio");
		Thread t1 = new BusyThread();
		t1.start();
		try {
			Thread.sleep(100); // Dorme un po’ per accertarsi che t1 vada in esecuzione
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		}
		Thread t2 = new MyThread();
		t2.setName("ciao");
		t2.start();
	}
}
