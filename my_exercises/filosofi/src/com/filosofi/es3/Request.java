public class Request{

	private philID;
	private leftChopstickID;
	private rightChopstickID;

	public Request(int philID, int leftChopstickID, int rightChopstickID){
		this.philID = philID;
		this.leftChopstickID = leftChopstickID;
		this.rightChopstickID = rightChopstickID;
	}

	public int getPhilID(){
		return this.philID;
	}

	public int getLeftChopstickID(){
		return this.leftChopstickID;
	}

	public int getRightChopstickID(){
		return this.rightChopstickID;
	}

	public String toString(){
		return "PhilID: "+this.philID+ 
			+" leftID: "+leftChopstickID+" rightID"+rightChopstickID;
	}

}