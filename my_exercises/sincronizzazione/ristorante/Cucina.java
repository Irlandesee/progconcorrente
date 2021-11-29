import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Cucina{

	private PriorityBlockingQueue<String> ordiniDaServire;
	private LinkedBlockingQueue<String> ordiniReady;
	private final int maxOrders = 4; //numero massimo di ordini preparabili contemporantemente

	public Cucina(){
		ordiniDaServire = new PriorityBlockingQueue<String>();
		ordiniReady = new LinkedBlockingQueue<String>();
	}

	/**
	 * Inserisce il nuovo ordine nella coda 
	 * */
	public synchronized void putOrder(String order){
		if(ordiniDaServire.size() == maxOrders){
			try{wait();}catch(InterruptedException ie){ie.printStackTrace();}
		}
		ordiniDaServire.put(order);
		notify();
	}

	/**
	 * Ritorna il prossimo ordine più anziano
	 * */
	public synchronized String getNextOrder(){
		if(ordiniDaServire.size() == 0){
			try{wait();}catch(InterruptedException ie){ie.printStackTrace();}
		}
		notify();
		return ordiniDaServire.poll();
	}

	/**
	 * Controlla lo stato dell'ordine
	 * */
	public synchronized boolean isOrderReady(String order){
		if(!(ordiniReady.contains(order)))
			return false;
		return true;
	}

	/**
	 * Preparazione dell ordine
	 * */
	public synchronized void prepare(String order){
		System.out.println("Preparing order: "+order);
		new CookingActivity(this, order);
	}

	/**
	 * 
	 * */
	public synchronized void finished(String order){
		ordiniReady.put(order);
		System.out.printf("Order %s ready\n", order);
		notify();
	}

}