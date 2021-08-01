public class Solution extends Thread{

	private int number;

	public Solution(int number){
		this.number = number;
		this.start();
	}

	public void run(){
		for(int i = 0; i < 10; i++)
			System.out.println("<<Thread>> : "+this.getNumber());
	}

	public int getNumber(){
		return this.number;
	}

}