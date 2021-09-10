package servedbeers;

import client.*;

import java.util.ArrayList;
import java.util.List;

public class ServedBeers{

	private Musician[] musicians;
	private Clients[] clients;

	private List<String> servedMusicians;

	public ServedBeers(){
		servedMusicians = new ArrayList<String>();
	}

	public synchronized void serve(String s){
		servedMusicians.add(s);
		notifyAll();
	}

	public synchronized boolean fetch(){
		long startTime = System.currentTimeMillis();
		long waitTime = 400;
		while(!servedMusicians.contains(s) && waitTime > 0){
			try{
				wait(waitTime);
			}catch(InterruptedException ie){ }
			waitTime = Math.max(0, waitTime - (System.currentTimeMillis() - startTime));
		}
		if(servedMusicians.contains(s)){
			servedMusicians.remove(s);
			return true;
		}
		else
			return false;
	}

}
