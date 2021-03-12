public class Concurrent extends Thread{
	private int myNum;
	public Concurrent(int n) {
		myNum=n;
		
	}
	public void run() {
		try {
			Thread.sleep((int) (Math.random()*100));
			System.out.println("In run, myNum="+myNum);
			Thread.sleep((int) (Math.random()*100));
			System.out.println("In run, myNum="+myNum);
		} catch (InterruptedException e) { }
	}
	public static void main(String args []) {
		Concurrent a=new Concurrent(1);
		Concurrent b=new Concurrent(2);
	    a.start();
	    b.start();
		try {
			Thread.sleep((int) (Math.random()*100));
			System.out.println("In main");
			Thread.sleep((int) (Math.random()*100));
			System.out.println("In main");
		} catch (InterruptedException e) { }
	}
}
