public class BroadcastExample {
	static Broadcast<String> broadcast=new Broadcast<String>();
	public static void main(String args[])
			throws InterruptedException {
		new AnsaMessage(broadcast);
		new Newspaper(broadcast, "IlCorriere.it").start();
		new Newspaper(broadcast, "IlFatto.it").start();
		new Newspaper(broadcast, "LaRepubblica.it").start();
		new Newspaper(broadcast, "IlMattino.it").start();
	}
}
