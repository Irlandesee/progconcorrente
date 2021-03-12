import java.util.concurrent.Semaphore;
public class Counter {
  private long count = 0;
  private Semaphore sem;
  public Counter() {
    sem = new Semaphore(1);
  }
  public void add (long value) {
    try {
      sem.acquire();
    } catch (InterruptedException e) { }
    this.count+=value;
    sem.release();
  }
  public long getValue(){
    return count;
  }
}
