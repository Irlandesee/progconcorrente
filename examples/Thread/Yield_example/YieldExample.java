
public class YieldExample {
	public static void main(String[] args) {
		for (int i=0; i<5; i++) {
			new Thread (new Decollo()).start();
		}
		System.out.println("In attesa del decollo");
	}
}