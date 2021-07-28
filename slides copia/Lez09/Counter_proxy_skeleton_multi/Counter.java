
public class Counter implements CounterInterface {
	private int theCounter;
	  public Counter() {
	    theCounter = 0;
	  }
	  public int sum(int s) {
	    theCounter += s;
	    return theCounter;
	  }
	  public int reset() {
	    theCounter = 0;
	    return theCounter;
	  }
	  public int increment() {
	    theCounter++;
	    return theCounter;
	  }
}
