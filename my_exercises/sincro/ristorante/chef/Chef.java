package chef;

public class Chef extends Thread{

	private Kitchen k;
	public Chef(Kitchen _k){
		k = _k;
		this.setName("The chef");
		start();
	}

	public void run(){
		while(true){
			try{
				String orderToServe = k.getNextOrder();
				System.out.println("Chef: im starting to prepare order:"
					+orderToServe);
				k.prepare(orderToServe);
			}catch(InterruptedException ie){
				break; //quando riceve interrupt esce dal ciclo e termina
			}
		}
		System.out.println("kitchen closed");
	}

}