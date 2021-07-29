
public class BasicThread extends Thread{

    BasicThread(){
		this.start();  // il thread diventa ready appena costruito
    }
	public void run(){
		for(int i=0;i<3;i++){
			System.out.println("Thread");
		}
	}
	public static void main(String[] args) throws InterruptedException {
		BasicThread t=new BasicThread();
//		Thread.sleep(1);  // scommentare per favorire inizio di thread 
		for(int i=0;i<3;i++){
			System.out.println("Main");
		}
	}
}
