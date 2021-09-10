package fil;

import java.util.concurrent.*;
import java.util.Queue;
import java.util.LinkedList;

public class Pool{

	private enum Poolstate {IDLE, ONWAIT, ACTIVE}
	//private Poolstate[] philStates;
	private int nSticks;

	private Queue<Poolstate> philStates;

	public Pool(int _nSticks, int nPhils){
		philStates = new LinkedList<Poolstate>(nPhils);
		nSticks = _nSticks;


		for(int i = 0; i < philStates.size(); i++){
			//philStates[i] = Poolstate.IDLE;
			philStates.add(PoolState.IDLE);
		}

	}

	/**
	 * Controlla se ce un filosofo in waiting
	 * */
	private boolean somePhilWaiting(){
		boolean anybodyWaiting = false;
		for(int i = 0; i < philStates.size(); i++){
			if(philStates.get(i) == Poolstate.ONWAIT){
				anybodyWaiting = true;
				break;
			}
		}
		return anybodyWaiting;
	}

	/**
	 * Cerca il primo filosofo onwait ritornando il suo indice
	 * metodo intelligente: cercare filosofo in piu tempo in wait
	 * coda?
	 * */
	private int pickWaitingPhil(){
		for(int i = 0; i < philStates.size(); i++){
			if(philStates.get(i) == Poolstate.ONWAIT)
				return i;
		}
		return -1;
	}

	public synchronized void takeTwo(int philID){
		System.out.printf("Pool: takeTwo called by phil: "+philID);
		philStates.set(philID, Poolstate.ONWAIT);
		notifyAll();
		while(philStates.get(philID) == Poolstate.ONWAIT){
			try{
				wait();
			}catch(InterruptedException ie){}
		}
		System.out.printf("Pool: two taken by Phil %d\n", philID);
	}

	public synchronized void leaveTwo(int philID){
		System.out.printf("Pool: two left by Phil: "+philID);
		nSticks += 2;
		philStates.set(philID, Poolstate.IDLE);
		Poolstate ps = philStates.remove();
		philStates.add(ps);
		notifyAll();
	}

	public synchronized void scheduleNext(){
		System.out.println("waiter trying to schedule next phil");
		while(nSticks < 2 || !somePhilWaiting()){
			try{wait();}catch(InterruptedException ie){}
		}
		int idPhil = pickWaitingPhil();
		if(idPhil == -1){
			System.out.println("Waiter: no luck!");
			try{Thread.sleep(100);}catch(InterruptedException ie){}
		}else{
			System.out.println("Waiter: next phil scheduled: "+idPhil);
			philStates.set(philID, Poolstate.ACTIVE);
			nSticks -= 2;
			notifyAll();
		}

	}

}