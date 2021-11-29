import java.util.ArrayList;
import java.util.List;

public class BeerAwaiters {
	private List<String> thistyClients;	
	BeerAwaiters(int num){
		thistyClients = new ArrayList<String>();
	}
	public synchronized void add(String clientName){
		if(!thistyClients.contains(clientName)){
			thistyClients.add(clientName);
			notify();  // non notifyAll perche' c'e` un solo Holder potenzialmente in attesa
		}
	}
	public synchronized String get(){
		while(thistyClients.isEmpty())
			try {
				wait();
			} catch (InterruptedException e) { }
		String clientToBeServedName=thistyClients.remove(0);
		return(clientToBeServedName);
	}
}
