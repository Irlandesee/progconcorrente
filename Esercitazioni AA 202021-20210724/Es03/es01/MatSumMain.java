
public class MatSumMain {
	int matrix[][]=
		{
				{ 1, 2, 3, 4, 5} ,
				{ 2, 2, 2, 2, 2 } ,
				{ 3, 3, 3, 3, 3 } ,
				{ 4, 4, 4, 4, 3 } ,
				{ 5, 5, 5, 5, 5 } } ;
	int rows = matrix.length;
	int cols = matrix[0].length;
	int results[];
	void printOut() {
		int total=0;
		System.out.print("[");
		for(int i=0; i<rows; i++){
			total+=results[i];
			System.out.print(results[i]+" ");
		}
		System.out.println("]");
		System.out.println("total = "+total);
	}
	void printMatrix() {
		for(int i=0; i<rows; i++){
			System.out.print("[");
			for(int j=0; j<cols; j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println("]");
		}
	}
	public void exec() {
		results=new int[rows];
		Result res=new Result(results, rows);
		printMatrix();
		for(int i=0; i<rows; i++) {
			new Summer(matrix, i, res).start();
		}
		while(!res.isCompleted()){
			System.out.println("Waiting...");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {	}
		}
		printOut();
	}
	public static void main(String[] args) {
		MatSumMain msm = new MatSumMain();
		msm.exec();
		System.out.println("Main: finito");
	}

}
