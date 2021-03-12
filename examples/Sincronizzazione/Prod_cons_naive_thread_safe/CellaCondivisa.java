
public class CellaCondivisa {
	int valore ;

	public synchronized int getValore() {
		System.out.print("Viene letto "+valore);
		return valore;
	}

	public synchronized void setValore(int valore) {
		System.out.print("Viene scritto "+valore);
		this.valore = valore;
	}
}
