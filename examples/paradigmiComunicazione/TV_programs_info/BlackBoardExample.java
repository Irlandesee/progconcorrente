import java.util.concurrent.ThreadLocalRandom;

public class BlackBoardExample {
	public static void main (String args[])
			throws InterruptedException {
		Blackboard<Programma> bbRai3 = new Blackboard<Programma>();
		Programma[] progs=new Programma[5];
		progs[0]=new Programma("Meteo 3", "20:05", 5);
		progs[1]=new Programma("Blob", "20:10", 15);
		progs[2]=new Programma("TG3", "20:25", 40);
		progs[3]=new Programma("Dott. Stranamore", "21:05", 120);
		progs[4]=new Programma("Notturno", "23:05", 420);
		new CanaleTv(bbRai3, "Rai3", progs).start();
		for(int i=0; i<8; i++) {
			Thread.sleep(ThreadLocalRandom.current().nextInt(30));
			new Utente(bbRai3).start();
		}
	}
}
