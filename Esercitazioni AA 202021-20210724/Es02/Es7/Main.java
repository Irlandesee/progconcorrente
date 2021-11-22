
public class Main {
	public static void main(String[] args) {
		String ns[]={"Ping", "Pong"};
		Table tab=new Table(ns);
		new PingPong(0, tab).start();
		new PingPong(1, tab).start();
	}
}
