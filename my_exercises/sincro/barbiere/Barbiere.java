public class Barbiere extends Thread{

	private Negozio neg;

	public Barbiere(Negozio _neg){
		this.setName("Barbiere");
		neg = _neg;
	}

	public void run(){
		while(true){
			neg.attesaDormiente();
			while(neg.ciSonoClientiInAttesa()){
				String cs = neg.primoClienteDaServire();
				neg.servizioCliente(cs);
				try{
					System.out.println("Taglio capelli");
					sleep(100); 
				}catch(InterruptedException ie){}
				neg.servito(cs);
			}
		}
	}


}