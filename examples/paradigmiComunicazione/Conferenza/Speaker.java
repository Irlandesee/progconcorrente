public class Speaker extends Thread {
	Broadcast<String>speechMessage;
	public Speaker(Broadcast<String> wm) {
		speechMessage=wm;
		setName("Speaker " + getName());
	}
	public void run() {
		System.out.println(getName()+": Inizia a parlare...");
		speechMessage.send("Benvenuti!");
		for(int i=0;i<10;i++) {
			try {
				Thread.sleep(100);
			} catch(InterruptedException e) { }
			speechMessage.send("Bla bla bla "+i);
		}
	}
}
