import java.util.concurrent.*;

public class Main{

	public static final int BUFSIZE = 4;


	public static void main(String[] args){
		BlockingQueue<String> q = new ArrayBlockingQueue<String>(BUFSIZE);
		new Produttore(13, q).start();			
		new Consumatore(99, q).start();
	
	}
	
}
