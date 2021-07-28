import java.util.concurrent.*;

public class ParMatSum {
	private static int matrix[][]=
		{
		{ 1, 2, 3, 4, 5} ,
		{ 2, 2, 2, 2, 2 } ,
		{ 3, 3, 3, 3, 3 } ,
		{ 4, 4, 4, 4, 3 } ,
		{ 5, 5, 5, 5, 5 } } ;
	public static int results[];

	public static void main(String args[]) throws InterruptedException {
		final int rows=matrix.length;    // number of rows
		final int cols=matrix[0].length; // number of columns
		for(int i=0; i<rows; i++){
			System.out.print("[");
			for(int j=0; j<cols; j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println("]");
		}
		
		results=new int[rows];
		CyclicBarrier bar=new CyclicBarrier(rows, new ResultUser(results));
		for(int i=0; i<rows; i++) {
			new RowSummer(i, matrix, results, bar).start();
		}
	}
}