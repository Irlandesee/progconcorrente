import java.util.concurrent.ThreadLocalRandom;

public class Writer extends Thread{

	private Data data;
	private String name;
	public Writer(String s, Data d){
		super(s);
		name = s;
		data = d;
	}

	public void run(){
		for(int j = 0; j < 10; j++){
			data.startWriting();
			System.out.println(name+" starts writing");
			try{
				Thread.sleep(ThreadLocalRandom.current().nextInt(50, 120));
			}catch(InterruptedException ie){ }
			data.finishedWriting()
			System.out.println(name+" stops writing");
			try{
				Thread.sleep(ThreadLocalRandom.current().nextInt(30 ,50));
			}catch(InterruptedException ie){}
		}
	}

}