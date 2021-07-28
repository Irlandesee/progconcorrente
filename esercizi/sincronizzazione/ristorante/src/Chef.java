public class Chef extends Thread{

	private Kitchen k;

	public Chef(Kitchen k){
		this.k = k;
		this.setName("Chef");
		start();
	}

	public void run(){
		while(true){
			k.getNextOrder();
		}
	}

}