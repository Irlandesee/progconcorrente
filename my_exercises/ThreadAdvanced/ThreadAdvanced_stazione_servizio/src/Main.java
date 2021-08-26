import solution.Auto;
import solution.Pompa;

public class Main{

	private static final int NUMAUTO = 10;

	public static void main(String[] args){
		Thread[] autoThreads = new Thread[NUMAUTO];
		try{
			Pompa laPompa = new Pompa();
			for(int i = 0; i < NUMAUTO; i++){
				Auto auto = new Auto(i, laPompa);
				(autoThreads[i] = new Thread(auto)).start();
			}
			for(int i = 0; i < NUMAUTO; i++)
				autoThreads[i].join();
		}catch(InterruptedException e){ }
		System.out.println("Tempo medio rifornimento = " +Auto.calcAvg());

	}

}