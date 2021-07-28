public class myapp {
  public static void main(String args[]) {
    // create an event source - reads from stdin
    final EventSource evSrc = new EventSource();
    // create an observer
    final InputHandler inHandler = new InputHandler();
    // subscribe the observer to the event source
    evSrc.addObserver(inHandler);
    // starts the event thread
    Thread evSrcThread = new Thread(evSrc);
    evSrcThread.start();
  }
}
