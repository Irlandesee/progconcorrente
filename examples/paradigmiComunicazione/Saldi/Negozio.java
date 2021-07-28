public class Negozio extends Thread {
	private Pulse portaNegozio;
	public Negozio(Pulse p) {
		portaNegozio=p;
		setName("Negozio" + getName());
	}
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				System.out.println(getName() + ": apro le porte");
				portaNegozio.sendAll();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
