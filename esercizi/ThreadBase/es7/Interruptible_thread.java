public class Interruptible_thread extends Thread{

	public void run(){
		for(;;){
			System.out.println("ciao");
			try{
				Thread.sleep(500);
			}catch(InterruptedException e){
				System.out.println("Thread termina per interrupt");
				break;
			}
		}
	}

}