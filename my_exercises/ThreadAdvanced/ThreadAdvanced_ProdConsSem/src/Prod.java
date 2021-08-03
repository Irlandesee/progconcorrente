
public class Prod extends Thread{
	private Solution s;
	public Prod(String name, Solution s){
		super(name);
		this.s = s;
	}

	public void run(){
		int i = 0;
		for(;;){
			try{
				ProdCons.empty.acquire();
			}catch(InterruptedException e){ }
			s.setValore(i++);
			ProdCons.full.release();
		}
	}
}