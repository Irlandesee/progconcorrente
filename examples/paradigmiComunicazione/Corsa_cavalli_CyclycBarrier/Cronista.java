
public class Cronista implements Runnable {
	public void run() {
		System.out.println("I cavalli partono per il giro "+CorsaDiCavalli.num_giri++);
	}
}
