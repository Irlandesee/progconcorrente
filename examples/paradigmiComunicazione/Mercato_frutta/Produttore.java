
public class Produttore extends Thread {
	BoundedBuffer<Frutta> mercato;
	double totaleSoldi=0;
	private String myName;

	public Produttore(String name, BoundedBuffer<Frutta> mercato) {
		this.setName(name);
		this.mercato=mercato;
		this.myName=getName();
	}

	public void run() {
		for(int i=0; i<20; ++i) {
			try {
				Frutta v=new Frutta(Math.random()*2.4+0.1);
				System.out.printf("%s: vende %.2f Kg\n", myName, v.getPeso()) ;
				mercato.put(v);
				totaleSoldi+=v.getPrezzo();
				System.out.printf("%s: guadagnato %.2f euro\n", myName, v.getPrezzo());
//				System.out.println(getName ( )+": Guadagnato " + v.getPrezzo() + " euro ") ;
			} catch(InterruptedException e) {}
		}
		System.out.printf("%s: guadagnato %.2f euro in Totale\n", myName, totaleSoldi);
//		System.out.println(getName()+": Guadagnato " + totaleSoldi + " euro in TOTALE");
	}
}
