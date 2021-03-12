import java.util.Scanner;

public class Main{

	public static void main(String[] args) throws InterruptedException{
		int n;
		Scanner in = new Scanner(System.in);
		Solution s;
		while((n = in.nextInt()) != 0){
			if(n > 0 && n < 6){
				s = new Solution(n);
				for(int i = 0; i < n; i++){
					System.out.println("Istanza del Thread: "+i);
					s.start();	
					
					System.out.println("Aspetto che questo thread muoia");
					s.join();
				}

			}
		}
	}


}

