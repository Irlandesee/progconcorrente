public class Main{

	public static void main(String[] args){
		Table t = new Table();
		new Giocatore(0, t).start();
		new Giocatore(1, t).start();

	}

}