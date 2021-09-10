
public class Summer extends Thread {
	int row;  // indice della riga su cui lavorare
	Result resRef;  // riferimento all'oggetto in cui segnalare che si e` finito
	int matrix[][];
	Summer(int m[][], int row, Result rr) {
		this.row=row;
		this.resRef=rr;
		this.matrix=m;
	}
	public void run() {
		int columns=matrix[row].length;  // numero di colonne
		int sum=0;
		for(int i=0; i<columns; i++) {
//			System.out.println("thread "+this.getName()+" deal with "+matrix[row][i]);
			sum += matrix[row][i];
		}
		System.out.println("thread "+this.getName()+" increments sums ");
		resRef.setSums(row, sum);
		System.out.println("Result for row "+row+" is: " + sum);
	}
}
