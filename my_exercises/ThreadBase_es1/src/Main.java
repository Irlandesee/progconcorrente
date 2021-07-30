public class Main extends Thread{

	public void run(){
		for(int i = 0; i < 3; i++)
			System.out.println("Thread..");
	}

	public static void main(String[] args){
		Main m = new Main();
		m.start();
		for(int i = 0; i < 3; i++)
			System.out.println("Main.");
	}

}