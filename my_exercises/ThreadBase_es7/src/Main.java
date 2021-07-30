import java.util.Scanner;

public class Main{

	public static void main(String[] args){
		BasicThread basicThread = new BasicThread("t1");

		Scanner in = new Scanner(System.in);
		String stringaLetta = "";
		while(true){
			stringaLetta = in.nextLine();
			if(stringaLetta.equals("fine")){
				basicThread.interrupt();
				System.out.println("<<main>> : termino");
				break;
			}
		}

	}

}