public class Main{

	public static void main(String[] args){
		Solution s = new Solution();
		new Thread(s).start();
		for(int i = 0; i < 3; i++)
			System.out.println("<<Main>>");
	}

}