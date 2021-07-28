

public class ProdConsFrutta {
	public static void main(String[] args) {
		BoundedBuffer<Frutta> mercato=new BoundedBuffer<Frutta>(8);
		for(int i=0; i<3; i++) {
			(new Produttore("P"+i, mercato)).start();
			(new Consumatore("C"+i, mercato)).start();
		}
	}
}