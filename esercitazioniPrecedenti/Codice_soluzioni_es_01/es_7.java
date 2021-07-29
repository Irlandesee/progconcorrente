import java.util.Scanner;


public class BasicThread extends Thread{

	public void run(){
		for(;;){
			System.out.println("ciao");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("thread interrotto: termino");
				break;
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		BasicThread t=new BasicThread();
		t.start();
		String cmd;
		Scanner sc = new Scanner(System.in);
		for(;;){
			System.out.println("Inserisci comando >");
			cmd=sc.next();
			if(cmd.equals("fine")){
				t.interrupt();
				System.out.println("Main ha letto il comando di terminazione ");
				break;
			} else {
				System.out.println("Main ha letto il comando "+cmd+" e lo ha ignorato");
			}
		}
	}
}

