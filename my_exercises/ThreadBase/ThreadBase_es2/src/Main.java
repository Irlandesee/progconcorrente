public class Main implements Runnable{

	public void run(){
		for(int i = 0; i < 3; i++)
			System.out.println("<<Thread>>");
	}

	public static void main(String[] args){
		Main m = new Main();
		Thread t1 = new Thread(m);
		t1.start();
		for(int i = 0; i < 3; i++)
			System.out.println("<<main>>");
	}

}