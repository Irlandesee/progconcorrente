import java.util.*;

public class Observable {

	protected List<Observer> observers = new ArrayList<Observer>();
	public void addObserver(Observer o) {
		observers.add(o);
	}
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	protected void notifyObservers(String str) {
		for(Observer o : observers) {
			o.update(this, str);
		}
	}
}
