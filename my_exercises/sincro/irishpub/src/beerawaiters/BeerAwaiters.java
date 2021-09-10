import java.util.ArrayList;
import java.util.List;

public class BeerAwaiters{
	private List<String> thirstyClients;

	public BeerAwaiters(int num){
		thirstyClients = new ArrayList<String>(num);
	}

	public synchronized void add(String clientName){
		if(!thirstyClients.contains(clientName)){
			thirstyClients.add(clientName);
			notify(); //holder
		}
	}

	public synchronized String get(){
		while(thirstyClients.isEmpty()){
			try{wait();}catch(InterruptedException ie){}
		}
		return thirstyClients.remove(0);;

	}

}