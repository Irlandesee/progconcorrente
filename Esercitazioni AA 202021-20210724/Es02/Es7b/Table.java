public class Table {
	private String aChiTocca;
	public Table() {
		this.aChiTocca="Ping";
	}
	private String nextUp() {
		if(aChiTocca.equals("Ping")) {
			return "Pong";
		} else {
			return("Ping");
		}
	}
	public synchronized void aspettoTurno(String chi){
		while(!aChiTocca.equals(chi)){
			try {
				wait();
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
	public synchronized void finito() {
		aChiTocca=nextUp();
		notifyAll();
	}
}



