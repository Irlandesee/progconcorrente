public class Solution extends Thread{

	public Solution(){
	}

	public void run(){
		for(int i = 0; i < 3; i++)
			System.out.println("Thread");
	}

}