import java.util.concurrent.ThreadLocalRandom;

public class Reader extends Thread{

	private Data data;
	private String name;
	
	public Reader(String s, Data d){
		super(s);
		name = s;
		data = d;
	}

	public void run(){
		for(int i = 0; i < 10; i++){
			data.startReading();
			System.out.println(name+" starts reading");
			try{
				Thread.sleep(ThreadLocalRandom.current().nextInt(5, 100));
			}catch(InterruptedException ie){ }
			System.out.println(name+" stops reading");
			data.finishedReading();
		}
		try{
			Thread.sleep(ThreadLocalRandom.current().nextInt(50, 120));
		}catch(InterruptedException ie){}
	}

}