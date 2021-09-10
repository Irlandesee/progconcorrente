
public class Pool {
	enum State {Idle, OnWait, Active};
	State[] philStates;
	int numSticks;
	Pool(int np, int ns) {
		philStates=new State[np];
		numSticks=ns;
		for(int i=0; i<Table.NUM_PHIL; i++) {
			philStates[i]=State.Idle; // inizializza lo stato dei plosofi
		}
	}
	private boolean somePhilWaiting() {
		boolean anybodyWaiting=false;
		for(int i=0; i<Table.NUM_PHIL; i++) {
			if(philStates[i]==State.OnWait) {
				anybodyWaiting=true;
				break;
			}
		}
		return anybodyWaiting;
	}
	private int pickWaitingPhil() {
		// dovrebbe essere fatto in modo intelligente
		// scegliendo il philosofo che e` a digiuno da piu` tempo
		for(int i=0; i<Table.NUM_PHIL; i++) {
			if(philStates[i]==State.OnWait) {
				return i;
			}
		}
		return -1;
	}
	public synchronized void takeTwo(int philId) {
		System.out.println("Pool: taketwo called by Phil "+philId);
		philStates[philId]=State.OnWait;
		notifyAll();
		while(philStates[philId]==State.OnWait) {
			try { wait(); } catch (InterruptedException e) {}
		}
		System.out.println("Pool: two taken by Phil "+philId);
	}
	public synchronized void leaveTwo(int philId) {
		System.out.println("Pool: two left by Phil "+philId);
		numSticks+=2;
		philStates[philId]=State.Idle;
		notifyAll();
	}
	public synchronized void scheduleNext() {
		System.out.println("Pool: waiter tries to schedule some Phil");
		while(numSticks<2 || !somePhilWaiting()) {
			try { wait(); } catch (InterruptedException e) {}
		}
		// pick a waiting Philosopher and wake it up
		int idP=pickWaitingPhil();
		if(idP==-1) {
			System.out.println("Waiter: niente da fare");
			try { Thread.sleep(100); } catch (InterruptedException e) {}
//			System.exit(0);
		} else {
			System.out.println("Pool: waiter wakes up Phil "+idP);
			philStates[idP]=State.Active;
			numSticks-=2;
			notifyAll();
		}
	}
}

