
public class CurrencyConverter implements CurrencyConverterInterface {
  public CurrencyConverter() { }
  public float toEur(float usd) {
     return usd*0.895415473F;
  }
  public float toUsd(float eur) {
     return eur*1.114365F;
}}
