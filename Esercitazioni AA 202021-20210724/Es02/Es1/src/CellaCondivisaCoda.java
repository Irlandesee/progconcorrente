public class CellaCondivisaCoda {
	static int BUFFERSIZE;
	private int numItems = 0;
	private int[] valori;
	private int first, last; // last is the index of the
	// most recently inserted item
	public CellaCondivisaCoda(int bufsize) {
		BUFFERSIZE=bufsize;
		first=0;
		last=0;
		valori=new int[BUFFERSIZE];
	}
	public synchronized int getItem(){
		int tmp;
		if(numItems==0){
			System.err.print("lettura di buffer vuoto!\n");
			System.exit(0);
		}
		numItems--;
		tmp=valori[first];
		first=(first+1)%BUFFERSIZE;
		System.out.print(tmp+" read\n");
		return tmp;
	}
	public synchronized void setItem(int v) {
		if(numItems==BUFFERSIZE){
			System.err.print("scrittura di buffer pieno!\n");
			System.exit(0);
		}
		valori[last]=v;
		last=(last+1)%BUFFERSIZE;
		numItems++;
		System.out.print(v+" written\n");
	}
	public int getCurrentSize(){
		return numItems;
	}
}



