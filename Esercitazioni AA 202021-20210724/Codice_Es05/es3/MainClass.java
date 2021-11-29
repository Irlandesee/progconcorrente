import java.util.concurrent.ThreadLocalRandom;

public class MainClass {
	public static void main(String[] args) {
		final int numClienti=12;
		Thread.currentThread().setName("main");
		Negozio negozio=new Negozio();
		Barbiere barb=new Barbiere(negozio);
		barb.start();
		for(int ic=0; ic<numClienti; ic++){
			new Cliente("Cliente_"+ic, negozio);
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(50,120));
			} catch (InterruptedException e) { }
		}
	}
}
