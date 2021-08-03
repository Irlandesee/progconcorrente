
public class Cons extends Thread{
	private Solution s;
	private int v;
	public Cons(String name, Solution s){
		super(name);
		this.s = s;
	}

	public void run(){
		for(;;){
			try{
				ProdCons.full.acquire(); //Si blocca a meno che buffer contiene un elemento
			}catch(InterruptedException e){ }
			v = s.getValore();
			ProdCons.empty.release();
		}
	}
}