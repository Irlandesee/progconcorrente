public class Philosopher extends Thread{

	private Pool pool;
	private String name;
	public Philosopher(String id, Pool p){
		this.name = id;
		this.pool = p;
	}

	public void printOut(String s){
		System.out.println("Phil :"+name+s);
	}

	public void doActivity(String s, int minTime, int maxTime){
		printOut(s);
		try{
			Thread.sleep(ThreadLocalRandom.current().nextInt(minTime, maxTime));
		}catch(InterruptedException e){}
	}

	public void run(){
		int c1, c2;
		while(true){
			try{
				doActivity(" Thinking ", 400, 500);
				printOut("hungry");
				while((c1 = pool.get_one_of_many()) == -1){
					doActivity("doing something")
				}
			}
		}
	}

}