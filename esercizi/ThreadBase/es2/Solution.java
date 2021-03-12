public class Solution implements Runnable{

	public Solution(){
	}

	public void run(){
		for(int i = 0; i < 3; i++)
			System.out.println("<<Thread>>");
	}

}