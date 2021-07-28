public class Table{

	private int turno;

	public Table(){
		//inizializza il giocatore con id = 0
		turno = 0;
	}

	public synchronized void aspettaTurno(int idGiocatore){
		while(idGiocatore != turno)
			try{
				wait();
			}catch(InterruptedException e){}

	}

	public synchronized void mossa(){
		turno = 1 - turno;
		notify(); 
	}

}