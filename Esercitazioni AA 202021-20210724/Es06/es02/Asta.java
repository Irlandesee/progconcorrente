public class Asta {
	private int offertaCorrente;
	private int baseAsta;
	private double rialzo_min;
	private String titolareOfferta;
	Asta(int ba, double rialzmin){
		this.offertaCorrente=ba;
		this.baseAsta=ba;
		this.rialzo_min=rialzmin;
		this.titolareOfferta="nessuno";
	}
	public synchronized int leggi_offerta() {
		return offertaCorrente;
	}
	public synchronized String leggi_titolare() {
		return titolareOfferta;
	}
	public synchronized boolean fai_offerta(int newOffer, String chi){
		if(newOffer >= offertaCorrente*(1+rialzo_min) && newOffer >= baseAsta){
			titolareOfferta=chi;
			offertaCorrente=newOffer;
			System.out.println("Asta: new offer by "+chi+", "+newOffer+" soldi");
			return(true);
		} else{
			return(false);
		}
	}


}
