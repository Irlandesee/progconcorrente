package solution;

public class Pompa{

	private static final int LIBERA = 0;
	private static final int OCCUPATA = 1;
	private int state = LIBERA;

	public Pompa(){
		state = LIBERA; 
	}

	public synchronized void occupa(){
		try{
			while(state != LIBERA)
				wait();
			state = OCCUPATA;
		} catch(InterruptedException e){ }
	}

	public synchronized void eroga(int attesa){
		try{
			while(state != OCCUPATA)
				wait();
			Thread.sleep(attesa);
		}catch(InterruptedException e){ }
	}

	public synchronized void lascia(){
		try{
			while(state != OCCUPATA)
				wait();
			Thread.sleep(40);
			state = LIBERA;
			notifyAll();
		}catch(InterruptedException e){ }
	}

}