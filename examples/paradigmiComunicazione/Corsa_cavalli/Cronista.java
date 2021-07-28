public class Cronista extends Thread {
	Barrier barriera ;
	private int numGiri ;
	public Cronista(Barrier b, int n) {
		barriera=b;
		setName("Cronista");
		numGiri=n;
	}
	public void run() {
		for(int i=0; i<numGiri; i++) {
			try {
				int c=barriera.waitCycle(i+1);
				System.out.println(this.getName()+": completato il giro num."+c);
			} catch(InterruptedException e) {}
		}
		System.out.println(this.getName()+": ultimo giro!");
	}
}
