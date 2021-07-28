import java.util.concurrent.ThreadLocalRandom;

class Utente extends Thread {
	String name ;
	Blackboard<Programma> blackboard;
	Utente(Blackboard<Programma> b) {
		this.blackboard=b;
		this.name="Utente" + getName();
	}
	public void run(){
		try {
			System.out.println(this.name + ": attendo un programma ");
			Programma msg = blackboard.read();
			System.out.println(this.name + ": sto guardando " + msg.toString());				
		} catch (InterruptedException e) { }
	}
}
