public class ProdCons {
	public static void main(String[] args) {
	    CellaCondivisa cella=new CellaCondivisa();
	    new Produttore("p1", cella).start();
	    new Produttore("p2", cella).start();
	    new Consumatore("c1", cella).start();
	    new Consumatore("c2", cella).start();
	}
}

