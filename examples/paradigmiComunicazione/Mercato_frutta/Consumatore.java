
public class Consumatore extends Thread {
	BoundedBuffer<Frutta> mercato;
	double pesoTotale=0;
	private String myName;

	public Consumatore(String name, BoundedBuffer<Frutta> mercato) {
		this.setName(name);
		this.mercato=mercato;
		this.myName=getName();
	}
	public void run() {
		for(int i=0; i<20; ++i) {
			try {
				Frutta v=mercato.get();
				pesoTotale+=v.getPeso();
				System.out.printf("%s: Comprati %.2f Kg di frutta \n", myName, v.getPeso());
				//	System.out.println(getName()+": Comprati " + v.getPeso()+" Kg di frutta ");
			} catch (InterruptedException e) {}
		}
		System.out.printf("%s: Comprati in totale %.2f Kg di frutta \n", myName, pesoTotale);
//		System.out.println(getName()+": Comprati " + pesoTotale + "Kg in TOTALE");
	}
}
