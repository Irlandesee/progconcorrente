public class Multi_thread extends Thread{


	int myID;
	public Multi_thread(int n){
		myID = n;
		this.start();
	}

	public void run(){
		for(int i = 0; i < 9; i++){
			System.out.println("Thread "+myID);
			try{
				Thread.sleep(100);
			}catch(InterruptedException e){}
		}
	}




}