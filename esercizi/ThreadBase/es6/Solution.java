public class Solution extends Thread{

	private int n;

	public Solution(int n){
		this.n = n;
	}

	public void run(){
		for(int i = 0; i < 10; i++)
			System.out.println("<<"+n+">>");
	}

}