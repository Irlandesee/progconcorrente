public class AssegnatorePosto {
	private int totPostiDisponibili=20;
	synchronized public boolean assegnaPosti(String cliente, int numPostiRichiesti){
		if(numPostiRichiesti<=totPostiDisponibili) {
			totPostiDisponibili-=numPostiRichiesti;
			return true;
		} else { return false; }
	}
	public int getPostiDisponibili(){
		return totPostiDisponibili;
	}
}
