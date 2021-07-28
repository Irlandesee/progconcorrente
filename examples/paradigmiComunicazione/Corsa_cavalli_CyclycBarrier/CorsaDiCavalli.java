import java.util.concurrent.CyclicBarrier;

public class CorsaDiCavalli {
	private static final int NUM_CAVALLI=3;
	private static final int NUM_GIRI=3;
	public static int num_giri=1;
	public static void main(String args[]) 
			throws InterruptedException {
		CyclicBarrier barriera=new CyclicBarrier(NUM_CAVALLI, new Cronista());
		for(int i=0; i<NUM_CAVALLI; i++) {
			new Cavallo(i+1, barriera, NUM_GIRI).start();
		}
	}
}
