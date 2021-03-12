public class Procedural {
	private int myNum;
	public Procedural(int n) {
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
		Procedural a=new Procedural(1);
		Procedural b=new Procedural(2);
	    a.run();
	    b.run();
		try {
			Thread.sleep((int) (Math.random()*100));
			System.out.println("In main");
			Thread.sleep((int) (Math.random()*100));
			System.out.println("In main");
		} catch (InterruptedException e) { }
	}
}
