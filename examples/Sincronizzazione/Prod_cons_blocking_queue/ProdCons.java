import java.util.concurrent.*;

public class ProdCons {
	static final int queueSize=4;
  public static void main(String[] args) {
    BlockingQueue<Message> queue = new ArrayBlockingQueue<Message>(queueSize);
    Producer producer=new Producer(queue);
    Consumer consumer=new Consumer(queue);
    new Thread(producer, "prod-1").start();
    new Thread(consumer, "cons-1").start();
    new Thread(producer, "prod-2").start();
    new Thread(consumer, "cons-2").start();
  }
}

