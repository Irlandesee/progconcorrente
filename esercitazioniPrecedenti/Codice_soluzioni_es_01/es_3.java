
public class BasicThread extends Thread{

	BasicThread(String name){
		super(name);
	}
	public void run(){
		for(int i=0;i<3;i++){
			System.out.println(this.getName());
		}
	}
	public static void main(String[] args) throws InterruptedException {
		BasicThread t=new BasicThread("my thread");
		t.start();
//		Thread.sleep(1);
		for(int i=0;i<3;i++){
			System.out.println("Main");
		}
	}
}

