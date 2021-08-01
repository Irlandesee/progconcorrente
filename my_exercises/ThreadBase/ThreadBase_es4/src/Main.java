public class Main implements Runnable{

	public void run(){
		for(int i = 0; i < 3; i++)
			System.out.println("<<Thread>> : "
				+Thread.currentThread().getName());
	}

	public static void main(String[] args){
		Thread t1 = new Thread(new Main());
		t1.setName("t1");
		t1.start();
		for(int i = 0; i < 3; i++)
			System.out.println("<<main>> : "
				+Thread.currentThread().getName());
	}

}