public class Barbiere extends Thread {
	private Negozio ilMioNegozio;
	public Barbiere(Negozio n){
		this.setName("Barber");
		ilMioNegozio=n;
	}
	public void run(){
		while(true){
			ilMioNegozio.attesaDormiente();
			while(ilMioNegozio.ciSonoClientiInAttesa()){
				String clienteDaServire=ilMioNegozio.primoClienteDaServire();
				ilMioNegozio.servizioCliente(clienteDaServire);
				try {
					sleep(100); // taglio
				} catch (InterruptedException e) { }
				ilMioNegozio.servito(clienteDaServire);
			}
		}
	}
}
