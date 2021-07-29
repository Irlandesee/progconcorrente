
public class BasicThread implements Runnable{

	public void run(){
		for(int i=0;i<3;i++){
			System.out.println(Thread.currentThread().getName());
		}
	}
	public static void main(String[] args) throws InterruptedException {
		BasicThread r=new BasicThread();
		new Thread(r, "pippo").start();
//		Thread.sleep(1);
		for(int i=0;i<3;i++){
			System.out.println("Main");
		}
	}

}
