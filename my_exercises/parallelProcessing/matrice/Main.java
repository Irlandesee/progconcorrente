import java.util.Random;

import matrix.Matrix;
import solution.Solution;

public class Main{


	private static int nextInt(){
		Random rand = new Random();
		int bound = 100;

		return rand.nextInt(bound);
	}

	public static void main(String[] args){
		if(args.length < 2)
			System.out.println("Inserisci righe e colonne!");
		else{
			System.out.printf("righe: %d, colonne: %d\n", Integer.parseInt(args[0]),
				Integer.parseInt(args[1]));
			Main main = new Main();

			int rows = Integer.parseInt(args[0]);
			int columns = Integer.parseInt(args[1]);

			Matrix m = main.buildMatrix(rows, columns);
			main.printMatrix(m);

			System.out.println("Main: Calculating sum rows");
			Solution s = new Solution(m);
			int[] sumRows = s.calcSumRows();
			for(int i = 0; i < sumRows.length; i++){
				if(i == sumRows.length-1)
					System.out.printf("[%d]\n", sumRows[i]);
				else	
					System.out.printf("[%d]", sumRows[i]);
			}
		}
	}

	private Matrix buildMatrix(int rows, int columns){
		Matrix m = new Matrix(rows, columns);
		int[][] data = new int[rows][columns];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				data[i][j] = Main.nextInt();
			}
		}
		m.setData(data);
		return m;
	}	

	private void printMatrix(Matrix m){
		int[][] data = m.getData();
		for(int i = 0; i < m.getRows(); i++){
			for(int j = 0; j < m.getColumns(); j++){
				if(j == m.getColumns()-1)
					System.out.printf("[%d]\n", data[i][j]);
				else
					System.out.printf("[%d]", data[i][j]);
			}
		}
	}

}