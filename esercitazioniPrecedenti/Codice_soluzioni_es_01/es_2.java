
public class BasicThread implements Runnable{

	public void run(){
		for(int i=0;i<3;i++){
			System.out.println("Thread");
		}
	}
	public static void main(String[] args) throws InterruptedException {
		BasicThread r=new BasicThread();
		new Thread(r).start();
		Thread.sleep(1);
		for(int i=0;i<3;i++){
			System.out.println("Main");
		}
	}

}
