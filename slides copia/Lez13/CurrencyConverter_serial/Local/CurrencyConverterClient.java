import java.util.Random;

public class CurrencyConverterClient {
	void exec() {
		Conversion conv;
		Random rnd=new Random();
		int times=2+rnd.nextInt(10);
		float amount;
		String fromCurrency, toCurrency;
		CurrencyConverter stub = new CurrencyConverter();
		for(int i=0; i<times; i++) {
			try {
				if(rnd.nextBoolean()) {
					fromCurrency="USD";
					toCurrency="EUR";
				} else {
					fromCurrency="EUR";
					toCurrency="USD";
				}
				conv = new Conversion(rnd.nextFloat()*100, fromCurrency, toCurrency);
				conv=stub.compute(conv);
				System.out.println(conv);
			} catch (Exception e) {
				System.err.println("Client exc.: " + e.toString());
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new CurrencyConverterClient().exec();
	}
}
