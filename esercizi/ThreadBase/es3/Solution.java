public class Solution extends Thread{

	private String name;

	public Solution(String name){
		this.name = name;
	}

	public void run(){
		for(int i = 0; i < 3; i++)
			System.out.println("<<"+name+">>");
	}
}