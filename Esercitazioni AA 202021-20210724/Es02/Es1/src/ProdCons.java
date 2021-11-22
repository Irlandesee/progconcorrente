
public class ProdCons {
	public final static int BUFSIZE=4;
	public static void main(String[] args) {
		CellaCondivisaCoda cella=new CellaCondivisaCoda(BUFSIZE);
		new Produttore(cella).start();
		new Consumatore(cella).start();
	}
}


