
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
	public int getItem(){
		int tmp;
		try{
			ProdCons.full.acquire();
		} catch(InterruptedException e) {}
		try{
			ProdCons.mutex.acquire();
		} catch(InterruptedException e) {}
		numItems--;
		tmp=valori[first];
		first=(first+1)%BUFFERSIZE;
		System.out.print(tmp+" read\n");
		ProdCons.empty.release();
		ProdCons.mutex.release();
		return tmp;
	}
	public void setItem(int v) {
		try{
			ProdCons.empty.acquire();
		} catch(InterruptedException e) {}
		try{
			ProdCons.mutex.acquire();
		} catch(InterruptedException e) {}
		valori[last]=v;
		last=(last+1)%BUFFERSIZE;
		numItems++;
		System.out.print(v+" written\n");
		ProdCons.full.release();
		ProdCons.mutex.release();
	}
	public int getCuttentSize(){
		return numItems;
	}
}


