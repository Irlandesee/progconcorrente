
public class Solution{

	public static final int BUFFERSIZE = 1;
	private int numItems = 0;
	private int valore;

	public Solution(){

	}

	public void printWithName(String s){
		System.out.printf("%s %s %d [%d]", 
			Thread.currentThread().getName(), s, valore, numItems);
	}

	public int getCurrentSize(){
		return numItems;
	}

	public int getValore(){
		int tmp;
		try{
			ProdCons.mutex.acquire();
		}catch(InterruptedException e){ }
		tmp = valore;
		numItems--;
		printWithName(" letto ");
		ProdCons.mutex.release();
		return tmp;
	}

	public void setValore(int v){
		try{
			ProdCons.mutex.acquire();
		}catch(InterruptedException e){ }
		valore = v;
		numItems++;
		printWithName(" scritto ");
		ProdCons.mutex.release();
	}

}