package matrix;

public class Matrix{


	private int rows;
	private int columns;

	private int[][] data;

	public Matrix(int _rows, int _columns){
		rows = _rows;
		columns = _columns;

		data = new int[rows][columns];
	}

	public int getRows(){
		return this.rows;
	}

	public int getColumns(){
		return this.columns;
	}

	public int[][] getData(){
		return this.data;
	}

	public void setData(int[][] _data){
		data = _data;
	}

	public int[] getRow(int index){
		return data[index];
	}

}