
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

class Auto implements Runnable {
	private static int tempoTotale = 0,	numAuto = 0;
	private int numCliente;
	private Pompa pompaUsata;
	public Auto(int n, Pompa p) {
		this. pompaUsata = p;
		this. numCliente = n;
	}
	public static float calcMedia () {
		return tempoTotale/numAuto;
	}
	public void run () {
		final int MAXattesa= 3000;
		long tempoInizio, tempoFine;
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(1, MAXattesa));
		} catch (InterruptedException e) { }
		System.out.println("Auto " + numCliente+ " arriva a pompa");
		tempoInizio = (new Date()).getTime();
		pompaUsata.occupa();
		System.out.println("Auto "+ numCliente+ " in rifornimento");
		try { Thread.sleep(100);
		} catch (InterruptedException e) { }
		pompaUsata.eroga(ThreadLocalRandom.current().nextInt(300, 500));
		System.out.println ("Auto "+ numCliente+ " lascia pompa");
		pompaUsata.lascia();
		tempoFine = (new Date()).getTime();
		System.out.println("Tempo auto "+ numCliente+ " = "+ (tempoFine-tempoInizio));
		synchronized(Auto.class) {
			tempoTotale+=(tempoFine-tempoInizio); numAuto++;
		}
	}
}


