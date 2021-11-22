public class PingPong extends Thread {
	int myId;
	Table table;
	public PingPong(int id, Table t){
		this.myId=id;
		this.table=t;
	}
	public void run(){
		for(;;){
			table.aspettoTurno(myId);
			System.out.println(table.getTableName(myId));
			table.finito();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
	}
}

