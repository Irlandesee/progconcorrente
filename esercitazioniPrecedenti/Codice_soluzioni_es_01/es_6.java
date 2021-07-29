import java.util.Scanner;

public class BasicThread extends Thread{
	int myID;
	BasicThread(int n){
		myID=n;
		this.start();
	}
	public void run(){
		for(int i=0;i<3;i++){
			System.out.println("thread "+myID);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread[] myThreads;
		Scanner sc = new Scanner(System.in);
		System.out.println("Inserisci numero thread >");
		int NUMthreads=sc.nextInt();
		myThreads = new  Thread[NUMthreads];
		for(int i=0; i<NUMthreads; i++){
			myThreads[i]=new BasicThread(i);
		}
		for(int i=0;i<3;i++){
			System.out.println("Main");
		}
		for(int i=0; i<NUMthreads; i++){
			myThreads[i].join();
		}
		System.out.println("main termina");
	}
}
