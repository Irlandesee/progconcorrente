package solution;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Auto implements Runnable{
	private static int tempoTotale = 0;
	private static int numAuto = 0;

	private int numCliente;
	private Pompa pompaUsata;
	public Auto(int n, Pompa p){
		this.pompaUsata = p;
		this.numCliente = n;
	}

	public static float calcAvg(){
		return tempoTotale / numAuto;
	}

	public void run(){
		final int MAXATTESA  = 3000;
		long tempoInizio, tempoFine;
		try{
			Thread.sleep(ThreadLocalRandom.current().nextInt(1, MAXATTESA));
		}catch(InterruptedException e){ }
		System.out.printf("Auto %d arriva a pompa\n", numCliente);
		tempoInizio = (new Date()).getTime();
		pompaUsata.occupa();
		System.out.printf("Auto %d in rifornimento\n", numCliente);
		pompaUsata.eroga(ThreadLocalRandom.current().nextInt(300, 500));
		System.out.printf("Auto %d lascia pompa\n", numCliente);
		pompaUsata.lascia();
		tempoFine = (new Date()).getTime();
		System.out.printf("Tempo auto %d = %d\n", numCliente, (tempoFine-tempoInizio));
		synchronized(Auto.class){
			tempoTotale += (tempoFine-tempoInizio);
			numAuto++;
		}
	}

}