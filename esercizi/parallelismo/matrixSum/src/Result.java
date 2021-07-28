public class Result{

	private int[] r;
	private int receivedSums = 0;
	private int expectedSums;

	public Result(int[] r){
		this.r = r;
		expectedSums = r.length;
	}

	public synchronized void setSum(int row, int sum){
		r[row] = sum;
		receivedSums++; 
	}

	public boolean isCompleted(){
		return (receivedSums == expectedSums);
	}

}