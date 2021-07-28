public class Giocatore extends Thread{

	private int id;
	private Table t;

	public Giocatore(int id, Table t){
		this.id = id;
		this.t = t;
	}	

	public void run(){
		for(;;){
			//aspetta il proprio turno
			t.aspettaTurno(this.id);
			System.out.println("Thread " +id + " fa la sua mossa");
			//fa la sua mossa
			t.mossa();

		}
	}

}