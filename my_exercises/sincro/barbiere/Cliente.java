public class Cliente extends Thread{
	private String name;
	private Negozio barb;

	public Cliente(String _name, Negozio _barb){
		name = _name;
		this.setName(name);
		barb = _barb;
		start();
	}

	public void run(){
		if(barb.possoEntrare(name)){
			barb.attesaTurno(name);
			barb.attesaFineTaglio(name);
		}
	}
}