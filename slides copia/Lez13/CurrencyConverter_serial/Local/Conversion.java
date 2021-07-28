public class Conversion {
	float givenAmount;
	String givenCurrency;
	float targetAmount;
	String targetCurrency;
	Conversion(float inAmount, String inCurrency, String outCurrency){
		givenAmount=inAmount;
		givenCurrency=inCurrency;
		targetCurrency=outCurrency;
		targetAmount=-1;
	}
	public float getGivenAmount() {
		return givenAmount;
	}
	public String getGivenCurrency() {
		return givenCurrency;
	}
	public float getTargetAmount() {
		return targetAmount;
	}
	public String getTargetCurrency() {
		return targetCurrency;
	}
	public void setTargetAmount(float targetAmount) {
		this.targetAmount = targetAmount;
	}
	public String toString() {
		return ""+givenAmount+" "+givenCurrency+" = "+targetAmount+" "+targetCurrency;
	}
}
