public class Ascoltatore extends Thread {
	Broadcast<String> speechMessage;
	public Ascoltatore(Broadcast<String> wm) {
		speechMessage=wm;
		setName("Ascoltatore" + getName());
	}
	public void run() {
		try {
			System.out.println(getName()+" in attesa di ascoltare");
			int numAscolti=(int)(Math.random()*10);
			for(int i=0; i<numAscolti; i++) {
				String msg=speechMessage.receive();
				System.out.println(getName()+" ha sentito: "+msg);
			}
			System.out.println(getName()+" esce");
		} catch(InterruptedException e) {}
	}
}
