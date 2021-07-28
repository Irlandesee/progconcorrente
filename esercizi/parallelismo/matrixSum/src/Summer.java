public class Summer extends Thread{

	private int[][] m; //matrice
	private int i;	//riga
	private Result rr;

	public Summer(int[][] m, int i, Result rr){
		this.m= m;
		this.i = i;
		this.rr = rr;
	}

	public void run(){
		int columns = m[i].length;
		int sum = 0;
		for(int j = 0; j < columns; j++){
			sum += m[i][j];
		}
		rr.setSum(i, sum);
	}

}