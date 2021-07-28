
public class Frutta {
	private double qty;
	Frutta(double q){
		this.qty=q;
	}
	public double getPrezzo() {
		return (this.qty*this.qty)/2;
	}
	public double getPeso() {
		return this.qty;
	}
}
