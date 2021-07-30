public class Main extends Thread{

	public void run(){
		String threadName = Thread.currentThread().getName();
		for(int i = 0; i < 3; i++){
			System.out.println("<<Thread>> : "+threadName);
		}
	}

	public static void main(String[] args){
		Thread t1 = new Main();
		t1.setName("t1");
		t1.start();
		for(int i = 0; i < 3; i++)
			System.out.println("<<Main>> : " + 
				Thread.currentThread().getName());
	}

}