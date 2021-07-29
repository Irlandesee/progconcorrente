
public class BasicThread extends Thread{
	public void run(){
		for(int i=0;i<3;i++){
			System.out.println("Thread");
		}
	}
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		BasicThread t=new BasicThread();
		t.start();
//		Thread.sleep(1);
		for(int i=0;i<3;i++){
			System.out.println("Main");
		}
	}
}

