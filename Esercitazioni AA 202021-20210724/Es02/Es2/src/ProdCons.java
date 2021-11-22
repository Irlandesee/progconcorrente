
public class ProdCons {
	public final static int BUFSIZE=4;
	public final static int NumCons=3;
	public final static int NumProd=2;
	public static void main(String[] args) {
		CellaCondivisaCoda cella=new CellaCondivisaCoda(BUFSIZE);
		Thread Produttori[] = new Produttore[NumProd];
		Thread Consumatori[] = new Consumatore[NumCons];
		for(int i =0; i<NumProd; i++){
			Produttori[i]=new Produttore(cella, i);
			Produttori[i].start();
		}
		for(int i =0; i<NumCons; i++){
			Consumatori[i]=new Consumatore(cella, i);
			Consumatori[i].start();
		}
	}
}


