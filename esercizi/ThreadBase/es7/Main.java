import java.util.Scanner;

public class Main{


	public static void main(String [] args){
		Thread t = new Interruptible_thread();
		Scanner sc = new Scanner(System.in);
		t.start();

		for(;;){
			System.out.println("Inserisci comando");
			String cmd = sc.next();

			if(cmd.equals("fine")){
				t.interrupt();
				break;
			}	
			else{
				System.out.println("Comando Ignorato");
			}

		}
		try{
			t.join();
		}catch(InterruptedException e){}
		System.out.println("Main termina");


	}

}