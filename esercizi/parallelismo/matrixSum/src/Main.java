public class Main{

	private int matrix[][] =
	{
		{1, 2, 3, 4, 5},
		{2, 2, 2, 2, 2},
		{4, 4, 4, 4, 4},
		{8, 8, 8, 8, 8}
	};

	private int rows = matrix.length;
	private int cols = matrix[0].length;
	private int result[];
 
	public void printMatrix(){
		for(int i = 0; i < rows; i++){
			System.out.print("{");
			for(int j = 0; j < cols; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println("}");
		}
	}

	public void exec(){
		printMatrix();
		result = new int[rows];
		Result r = new Result(result);
		for(int i = 0; i < rows; i++){
			new Summer(matrix, i, r).start();
		}
		while(!r.isCompleted()){
			System.out.println("Main in wait");
			try{
				Thread.sleep(100);	
			}catch(InterruptedException e){}
		}
		printOut();
	}

	public void printOut(){
		int total = 0;
		System.out.print("{");
		for(int i = 0; i < rows; i++){
			total += result[i];
			System.out.print(total + " ");
		}
		System.out.println("}");
	}

	public static void main(String[] args){
		Main m = new Main();
		m.exec();
		System.out.println("Main finito");
	}

}