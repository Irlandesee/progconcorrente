
public class Counter implements CounterInterface {
	private int theCounter;
	  public Counter() {
	    theCounter = 0;
	  }
	  public synchronized int sum(int s) {
	    theCounter += s;
	    return theCounter;
	  }
	  public synchronized int reset() {
	    theCounter = 0;
	    return theCounter;
	  }
	  public synchronized int increment() {
	    theCounter++;
	    return theCounter;
	  }
}
