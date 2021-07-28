public class CorsaDiCavalli {
	private static final int NUM_CAVALLI=3;
	private static final int NUM_GIRI=3;
	public static void main(String args[]) throws InterruptedException {
		Barrier barriera=new Barrier(NUM_CAVALLI);
		for(int i=0; i<NUM_CAVALLI; i++) {
			new Cavallo(i+1, barriera, NUM_GIRI).start();
		}
		new Cronista(barriera, NUM_GIRI).start();
	}
}
