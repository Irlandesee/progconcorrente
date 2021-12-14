public class Asta {
	private Offerta offertaCorrente;
	private int baseAsta;
	private double rialzo_min;
	Asta(Offerta ba, double rialzmin){
		this.offertaCorrente=ba;
		this.baseAsta=ba.getAmount();
		this.rialzo_min=rialzmin;
	}
	public synchronized Offerta leggi_offerta() {
		return offertaCorrente;
	}
	public synchronized boolean nuova_offerta(Offerta o){
		int newOffer = o.getAmount();
		if(newOffer >= offertaCorrente.getAmount()*(1+rialzo_min) && newOffer >= baseAsta){
			offertaCorrente=o;
			return(true);
		} else{
			return(false);
		}
	}
}
