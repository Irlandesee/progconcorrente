
public class Cavallo extends Thread {
	private int numGiri;
	Barrier barriera;
	public Cavallo(int id, Barrier b, int n) {
		barriera=b;
		setName("Cavallo " + id);
		numGiri=n;
	}
	public void run() {
		System.out.println(getName()+ " e` partito!");
		for(int i=0; i<numGiri; i++) {
			try {
				Thread.sleep((long)(Math.random()*3000));
				System.out.println(getName()+" sta per entrare nella barriera ");
				barriera.waitB();
				System.out.println(getName()+ " e` ripartito!");
			} catch (InterruptedException ex ) {}
		}
		System.out.println(getName()+ " termina la corsa!");
	}
}

