public class PreemptiveTest{

	public static void main(String[] args){
		System.out.println("------- Inizio del main. ---------");

		Thread t1 = new BusyThread();
		t1.start();
		try{
			t1.sleep(100); //Dorme per accettarsi che t1 vada in exec
		}	
		catch(InterruptedException e){
			e.printStackTrace();
		}
		Thread t2 = new MyThread();
		t2.setName("t2");
		t2.start();

	}

	private static class BusyThread extends Thread{
		public void run(){
			int a = 0;
			while(true){
				if(a > 100){ a = a++;}
				else{ a--;}
			}
		}
	}

	private static class MyThread extends Thread{
		public void run(){
			String str = Thread.currentThread().getName();
			while(true){ System.out.println(str);}
		}
	}
}