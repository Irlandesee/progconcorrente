
public class DaemonExample extends Thread{
	public DaemonExample() {
		setDaemon (true);  // il default e` false
	}
	public void run() {
		int count=0;
		while(true) {
			System.out.println("Ciao "+count++);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	public static void main(String args []) {
		DaemonExample t1=new DaemonExample();
	    t1.start();
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Main thread terminates.");
	}
}
