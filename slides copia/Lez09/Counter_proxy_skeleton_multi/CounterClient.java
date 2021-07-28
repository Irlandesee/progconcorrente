
public class CounterClient {
	void exec() {
		int numClients=4;
		for(int i=numClients; i>0; i--) {
			new CounterClientThread(i).start();
			System.out.println("Master client: thread "+i+" created");
			try { Thread.sleep(2);
			} catch (InterruptedException e) {	}
		}
	}
	public static void main(String[] args) throws Exception {
		new CounterClient().exec();
	}
}
