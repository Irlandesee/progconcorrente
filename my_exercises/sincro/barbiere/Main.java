import java.util.concurrent.ThreadLocalRandom;

public class Main{
	public static void main(String[] args){
		final int nCliente = 12;
		Thread.currentThread().setName("main");
		Negozio n = new Negozio();
		Barbiere b = new Barbiere(n);
		b.start();
		for(int ic = 0; ic < nCliente; ic++){
			new Cliente("Cliente_"+ic, n);
			try{
				Thread.sleep(ThreadLocalRandom.current().nextInt(50, 120));
			}catch(InterruptedException ie){}
		}
	}
}