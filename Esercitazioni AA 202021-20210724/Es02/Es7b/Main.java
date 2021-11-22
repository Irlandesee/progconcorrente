
public class Main {
	public static void main(String[] args) {
		Table tab=new Table();
		Runnable re=new PingPong(tab);
		new Thread(re, "Ping").start();
		new Thread(re, "Pong").start();
	}
}
