public class CellaCondivisa {
	static final int BUFFERSIZE = 4;
	private int valore, numItems = 0;
	void printWithName(String s) {
		System.out.println(Thread.currentThread().getName()+s+valore+"["+numItems+"]");		
	}
	public synchronized int getValore() {
		while(numItems==0){ 
			try { wait(); } catch (InterruptedException e) { }
		}
		numItems--;
		printWithName(" legge ");
		notifyAll();
		return valore;
	}
	public synchronized void setValore(int v) {
		while(numItems==BUFFERSIZE){
			try { wait(); } catch (InterruptedException e) { }
		}
		valore=v;
		numItems++;
		printWithName(" scrive ");
		notifyAll();
	}
}

