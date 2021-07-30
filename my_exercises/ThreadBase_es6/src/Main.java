import java.util.Scanner;

public class Main{

	public static void main(String [] args){

		Scanner in = new Scanner(System.in);

		String strLetta = "";
		System.out.println("Inserisci un numero compreso"
			+ "tra 1 e 5");
		
		while(!(strLetta = in.nextLine()).equals("")){
			System.out.println("Inserisci un numero compreso"
				+ "tra 1 e 5");
			int number = Integer.parseInt(strLetta);
			if(number >= 1 && number <= 5){
				Solution s = new Solution(number);
				try{
					s.join();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			else{
				System.out.println("Inserisci un numero compreso"
					+ "tra 1 e 5");
			}
		}
		
	}

}