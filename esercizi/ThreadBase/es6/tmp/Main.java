import java.util.Scanner;

public class Main{


	public static void main(String[] args){

		Thread[] tmp;
		Scanner sc = new Scanner(System.in);
		System.out.println("Inserisci numero thread");
		int n = sc.nextInt();

		tmp = new Thread[n];

		for(int i = 0; i < n; i++){
			tmp[i] = new Multi_thread(i);

		}

		for(int i = 0; i < n; i++){
			try{
				tmp[i].join();	
			}
			catch(InterruptedException e){}
		}

		System.out.println("Main termina");

	}


}