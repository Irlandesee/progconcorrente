
public class RunnableExample implements Runnable{
	public void run() {
		System.out.println("Ciao!");
	}
	public static void main(String args []) {
		RunnableExample re=new RunnableExample();
		Thread t1=new Thread(re);
	    t1.start();
//	    new Thread(new RunnableExample()).start();
	}
}
