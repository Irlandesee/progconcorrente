
public class CurrencyConverter implements CurrencyConverterInterface {
	public CurrencyConverter() { }
	float toEur(float usd) {
		return usd*0.895415473F;
	}
	float toUsd(float eur) {
		return eur*1.114365F;
	}
	public Conversion compute(Conversion conv) {
		float convertedAmount=0;
		if(conv.givenCurrency.equals("USD")) {
			convertedAmount=toEur(conv.getGivenAmount());
		}
		if(conv.givenCurrency.equals("EUR")) {
			convertedAmount=toUsd(conv.getGivenAmount());
		}
		conv.setTargetAmount(convertedAmount);
		return conv;
	}
}
