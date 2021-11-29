import java.util.Random;

public class Main{

	private static int nextInt(){
		Random rand = new Random();
		return rand.nextInt(30);
	}

	public static void main(String[] args){

		Barber b = new Barber();
		Client[] clients = new Client[30];
		System.out.println("Main: creating barber");
		b.start();
		for(int i = 0; i < clients.length; i++){
			Client c = new Client(b, i);
			clients[i] = c;
		}
		System.out.println("Main: starting clients");
		for(int i = 0; i < clients.length; i++){
			clients[i].start();
		}

		//wait for the clients to be done
		/**
		try{	
			for(int i = 0; i < clients.length; i++){
				clients[i].join();
			}
			b.join();
		}catch(InterruptedException ie){
			System.out.println("Main: InterruptedException -> ");
			ie.printStackTrace();
		}
		**/
	}

}