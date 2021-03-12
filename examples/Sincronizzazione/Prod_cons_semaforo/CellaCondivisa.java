public class CellaCondivisa {
	static final int BUFFERSIZE = 1;
	private int numItems = 0;
	private int valore;
	void printWithName(String s) {
		System.out.println(Thread.currentThread().getName()+s+valore+"["+numItems+"]");		
	}
	public int getValore(){
		int tmp;
		try{
			ProdCons.mutex.acquire();
		} catch(InterruptedException e) {}
		tmp=valore;
		numItems--;
		printWithName(" letto ");
		ProdCons.mutex.release();
		return tmp;
	}

	public void setValore(int v) {
		try{
			ProdCons.mutex.acquire();
		} catch(InterruptedException e) {}
		valore=v;
		numItems++;
		printWithName(" scritto ");
		ProdCons.mutex.release();
	}
	public int getCuttentSize(){
		return numItems;
	}
}

