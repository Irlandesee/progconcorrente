public class Main{

	public static void main(String[] args){
		BasicThread basicThread = new BasicThread();

		for(int i = 0; i < 3; i++)
			System.out.println("<<Main>>");
	} 

	private static  class BasicThread extends Thread{
		public BasicThread(){
			this.start();
		}

		public void run(){
			for(int i = 0; i < 3; i++)
				System.out.println("<<Thread>>");
		}

	}

}