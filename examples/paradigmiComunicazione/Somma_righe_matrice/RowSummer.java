import java.util.concurrent.*;

public class RowSummer extends Thread {
	private int row;  // indice della riga su cui lavorare
	private int matrix[][];
	private int results[];
	private CyclicBarrier myBarrier;
	public RowSummer(int row, int m[][], int r[], CyclicBarrier b) {
		this.row=row;
		this.matrix=m;
		this.results=r;
		this.myBarrier=b;
	}
	public void run() {
		int columns=matrix[row].length;  // numero di colonne
		int sum=0;
		for(int i=0; i<columns; i++) {
			sum += matrix[row][i];
		}
		results[row]=sum;
		System.out.println("Result for row "+row+" is: " + sum);
		try {
			myBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
