package holder;

import java.util.concurrent.ThreadLocalRandom;

import beerawaiter.BeerAwaiter;
import servedbeers.ServedBeers;

public class Holder extends Thread{

	private final int freeBeerAvailable = 30;
	
	private BeerAwaiter drinkers;
	private int freeBeersServed;

	private ServedBeers deliveredBeers;

	public Holder(BeerAwaiter _ba, ServedBeers _sb){
		drinkers = _ba;
		deliveredBeers = _sb;
		freeBeerServed = 0;	
	}

	public void run(){
		String toServe;
		for(int i = 1; i <= MAX_BEERS; i++){
			toServe = drinkers.get();
			System.out.printf("Holder %d, serving beer %d to: %s\n"
				Thread.currentThread().getId(), freeBeersServed, toServe);
			deliveredBeers.serve(toServe);
		}
		System.out.println("Holder: %d, the free beer has finished!"
			Thread.currentThread().getId());
	}

}