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
		while(numItems==0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		numItems--;
		tmp=valori[first];
		first=(first+1)%BUFFERSIZE;
		System.out.print(tmp+" read\n");
		notify();
		return tmp;
	}
	public synchronized void setItem(int v) {
		while(numItems==BUFFERSIZE){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		valori[last]=v;
		last=(last+1)%BUFFERSIZE;
		numItems++;
		System.out.print(v+" written\n");
		notify();
	}
	public int getCurrentSize(){
		return numItems;
	}
}



