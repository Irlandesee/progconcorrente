package solution;

import matrix.Matrix;


public class Solution{

	private Matrix m;

	public Solution(Matrix _m){
		m = _m;
	}

	public int[] calcSumRows(){
		int numberOfRows = m.getRows();
		int[] sumRows = new int[numberOfRows];
		Adder[] adderList = new Adder[numberOfRows];


		System.out.println("Starting adders");

		for(int i = 0; i < numberOfRows; i++){
			Adder a = new Adder(m.getRow(i), i);
			System.out.printf("Adder %d has started\n", a.getAdderID());
			adderList[i] = a;
			adderList[i].start();
		}

		System.out.println("Saving results into array");

		for(int j = 0; j < adderList.length; j++){
			try{
				adderList[j].join();
				System.out.printf("Thread %d joined\n", adderList[j].getAdderID());
			}
			catch(InterruptedException ie){ie.printStackTrace();}
		}

		int j = 0;
		for(int i = 0; i < adderList.length; i++){
			sumRows[i] = adderList[i].getResult();
		}

		return sumRows;
	}

}