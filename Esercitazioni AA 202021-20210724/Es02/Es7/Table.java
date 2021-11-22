public class Table {
	private int aChiTocca;
	String who[];
	public Table(String w[]) {
		who=w;
		this.aChiTocca=0;
	}
	public String getTableName(int idx) {
		return who[idx];
	}
	public synchronized void aspettoTurno(int chi){
		while(aChiTocca!=chi){
			try {
				wait();
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
	public synchronized void finito() {
		aChiTocca=1-aChiTocca;
		notifyAll();
	}
}



