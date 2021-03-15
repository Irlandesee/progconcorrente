import java.util.Scanner;

public class Main{

	public static void main(String[] args){
		int n;
		Scanner in = new Scanner(System.in);
		Solution s;
		while((n = in.nextInt()) != 0){
			if(n > 0 && n < 6){
				for(int i = 0; i < n; i++){
					s = new Solution(n);
					Thread t = new Thread(s);
					try{

						System.out.println("Istanza del Thread: "+i);
						t.start();	
					
						System.out.println("Aspetto che questo thread muoia");
						t.join();
					}catch(InterruptedException e){
						e.printStackTrace();
					}

				}
			}

		}
	}


}
