public class PingPong implements Runnable {
	Table table;
	public PingPong(Table t){
		this.table=t;
	}
	public void run(){
		String myId=Thread.currentThread().getName();
		for(;;){
			table.aspettoTurno(myId);
			System.out.println(myId);
			table.finito();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
}

