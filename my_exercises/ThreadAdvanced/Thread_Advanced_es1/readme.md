# esercizio 1
# Problem description
Realizzare un sistema con un produttore e un consumatore:
-> Usando un monnitor
-> La cella è thread-safe, ma non blocca i consumatori su buffer vuoto o i produttori su buffer pieno.

=> Si osservano problemi


# Monitor:
A monitor is something a thread can grab and hold, preventing all other threads from grabbing that same monitor and forcing them to wait until the monitor is released. This is what a synchronized block does.

# esercizio 1b

modificare la soluzione dell'esercizio 1 in modo che il sistema si comporti <<bene>>, cioè
-> Il produttore aspetta quando il buffer è pieno
-> Il consumatore aspetta quando il buffer è vuoto

# Esercizio 2
Si realizzi un sistema con più produttori e più consumatori.


# notes

producer :
se ce almeno 1 posto libero nel buffer
	produci un item
	inseriscilo
	continua
altrimenti
	dormi

consumer :
se ce almeno 1 posto occupato nel buffer
	prendi un item
	consumalo
	continua
altrimenti
	dormi

# misc
Thread local random:
ThreadLocalRandom is a combination of the ThreadLocal and Random classes (more on this later) and is isolated to the current thread.

class Producer implements Runnable {
   private final BlockingQueue queue;
   Producer(BlockingQueue q) { queue = q; }
   public void run() {
     try {
       while (true) { queue.put(produce()); }
     } catch (InterruptedException ex) { ... handle ...}
   }
   Object produce() { ... }
 }

 class Consumer implements Runnable {
   private final BlockingQueue queue;
   Consumer(BlockingQueue q) { queue = q; }
   public void run() {
     try {
       while (true) { consume(queue.take()); }
     } catch (InterruptedException ex) { ... handle ...}
   }
   void consume(Object x) { ... }
 }

 class Setup {
   void main() {
     BlockingQueue q = new SomeQueueImplementation();
     Producer p = new Producer(q);
     Consumer c1 = new Consumer(q);
     Consumer c2 = new Consumer(q);
     new Thread(p).start();
     new Thread(c1).start();
     new Thread(c2).start();
   }
 }
