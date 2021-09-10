package kitchen;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import cookingactivity.*;

public class Kitchen{

	private final int MAX_ACTIVITIES = 4;
	private LinkedList<String> ordersToServe;
	public HashSet<String> ordersReady;

	private int ongoingCookingActivities;

	public Kitchen(){
		ordersToServe = new LinkedList<String>();
		ordersReady = new HashSet<String>();
		ongoingCookingActivities = 0;
	}

	private void printReadyOrders(){
		System.out.print("ready: [");
		for(String s : ordersReady)
			System.out.print(s + " ");
		System.out.println("]");
	}

	private void printPendingOrders(){
		System.out.print("pending orders: [");
		for(String s : ordersToServe)
			System.out.print(s + " ");
		System.out.println("]");
	}

	public synchronized void putOrder(String order){
		System.out.println("kitchen takes order "+order);
		ordersToServe.add(order);
		notifyAll();
	}

	public synchronized boolean isOrderReady(String order){
		System.out.println("Checking order: "+order);
		while(!ordersReady.contains(order)){
			try{
				wait();
			}catch(InterruptedException ie){}
		}
		ordersReady.remove(order);
		return true;
	}

	public synchronized String getNextOrder() throws InterruptedException{
		String orderToServe = null;
		while(ordersToServe.size() == 0){
			System.out.println("No new orders, waiting");
			wait();
		}
		ordersReady.removeFirst(orderToServe);
		return true;
	}

	public synchronized void finished(String o){
		ordersReady.add(o);
		ongoingCookingActivities--;
		notifyAll();
	}

	public synchronized void prepare(String orderToServe){
		while(ongoingCookingActivities >= MAX_ACTIVITIES){
			try{
				wait();
			}catch(InterruptedException ie){}
		}
		ongoingCookingActivities++;
		new CookingActivity(this, orderToServe);
	}

}