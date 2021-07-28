
public class CurrencyConverterClient {
  public static void main(String[] args) {
      try {
        CurrencyConverter stub = new CurrencyConverter();
        for(int usd = 1; usd < 10; usd++){
          System.out.println(usd + " USD = " + stub.toEur(usd) + " EUR");
        }
        for(int eur = 1; eur < 10; eur++){
          System.out.println(eur + " EUR = " + stub.toUsd(eur) + " USD");
        }    
      } catch (Exception e) {
          System.err.println("Client exc.: " + e.toString());
          e.printStackTrace();
      }
  }
}
