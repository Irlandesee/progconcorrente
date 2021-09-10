package solution;

public class Adder extends Thread{

	private int[] row;
	private int result;
	private final int adderID;
	private boolean calculationComplete;

	public Adder(int[] _row, int _adderID){
		row = _row;
		adderID = _adderID;
		result = -1;
		calculationComplete = false;
	}

	public void run(){
		int ris = 0;
		System.out.printf("AdderThread %d: starting calculation\n",
			adderID);
		for(int i = 0; i < row.length; i++)
			ris += row[i];
		setResult(ris);
	}

	public int getResult(){
		return result;
	}

	private void setResult(int _result){
		result = _result;
		calculationComplete = true;
		System.out.printf("AdderThread %d: result set\n",
			adderID);
	}

	public int getAdderID(){
		return adderID;
	}

	public boolean getCalcComplete(){
		return calculationComplete;
	}

}