import java.util.concurrent.ThreadLocalRandom;
class LockObjects implements Runnable{
	private Object a,b;
	public LockObjects(Object o1, Object o2){
		this.a=o1; this.b=o2;
	}
	public void run(){
		try{
			Thread.sleep(ThreadLocalRandom.current().nextInt(10, 100));
			System.out.println("In run");
			synchronized(a) {
				System.out.println("First item locked");
				Thread.sleep(ThreadLocalRandom.current().nextInt(10,100));
				synchronized(b) {
					System.out.println("Lock worked "+a.toString()+", "+b.toString());
				}
			}
		} catch(InterruptedException e) { }
	}
}
