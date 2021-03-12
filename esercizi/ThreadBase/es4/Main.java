public class Main{

	public static void main(String[] args){

		Solution s = new Solution("Thread Solution");
		new Thread(s).start();

		for(int i = 0; i < 3; i++)
			System.out.println("<<Thread Main>>");

	}

}