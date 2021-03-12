public class CodaCondivisa {
	static int BUFFERSIZE;
	private int numItems = 0;
	private int[] valori;
	private int first, last; // last is the index of the
	// most recently inserted item
	public CodaCondivisa(int bufsize) {
		BUFFERSIZE=bufsize;
		first=0;
		last=0;
		valori=new int[BUFFERSIZE];
	}
	void printWithName(String s, int v) {
		System.out.println(Thread.currentThread().getName()+s+v+"["+numItems+"]");		
	}
	public int getItem(){
		int tmp;
		try{
			ProdCons.mutex.acquire();
		} catch(InterruptedException e) {}
		if(numItems==0){
			System.err.print("lettura di buffer vuoto!\n");
			System.exit(0);
		}
		numItems--;
		tmp=valori[first];
		first=(first+1)%BUFFERSIZE;
		printWithName(" letto ", tmp);
		ProdCons.mutex.release();
		return tmp;
	}
	public void setItem(int v) {
		try{
			ProdCons.mutex.acquire();
		} catch(InterruptedException e) {}
		if(numItems==BUFFERSIZE){
			System.err.print("scrittura di buffer pieno!\n");
			System.exit(0);
		}
		valori[last]=v;
		last=(last+1)%BUFFERSIZE;
		numItems++;
		printWithName(" scritto ", v);
		ProdCons.mutex.release();
	}
	public int getCuttentSize(){
		return numItems;
	}
}

