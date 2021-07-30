public class BasicThread extends Thread{

	private String threadName;

	public BasicThread(String threadName){
		this.threadName = threadName;
		this.start();
	}

	public String getThreadName(){
		return this.threadName;
	}

	public void run(){
		for(;;){
			System.out.println(getThreadName() + " : <<ciao>>");
			try{
				this.sleep(1000);
			}catch(InterruptedException e){
				System.out.println(getThreadName() + ": <<termino>>");
				//this.join();
				break;
			}
		}
	}

}