
public class MyThread extends Thread{
	public void run() {
		String str=Thread.currentThread().getName();
		while(true){
			System.out.println(str);
		}
	}
}
