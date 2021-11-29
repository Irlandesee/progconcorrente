import java.util.ArrayList;
import java.util.List;

public class Negozio {
	private final int NUM_SEDIE=3;
	enum statoCoda {EMPTY, FULL, SOMEONE};
    enum statoPoltrona {AVAILABLE, OCCUPIED};	
	private String clienteServito;
	private List<String> clientiInAttesa=new ArrayList<String>();
	private statoCoda coda;
	private statoPoltrona poltrona;
	
	public Negozio(){
		coda=statoCoda.EMPTY;
		poltrona=statoPoltrona.AVAILABLE;
		clienteServito="nessuno";
	}
	public synchronized boolean ciSonoClientiInAttesa(){
		return coda!=statoCoda.EMPTY;
	}
	public synchronized void attesaDormiente(){
		System.out.println("Il barbiere si addormenta");
		while(coda==statoCoda.EMPTY){
			try {
				wait();
			} catch (InterruptedException e) { }
		}		
		System.out.println("Il barbiere si sveglia");
	}
	public synchronized boolean possoEntrare(String nomeCliente){
		System.out.print(nomeCliente+" entra nel negozio");
		if(coda==statoCoda.FULL){
			System.out.println(" ed esce subito perche' non c'e` posto");
			return false;
		} else {
			System.out.println(" e si mette in coda");
			clientiInAttesa.add(nomeCliente);
			if(clientiInAttesa.size()==NUM_SEDIE){
				coda=statoCoda.FULL;
			} else {
				coda=statoCoda.SOMEONE;
			}
			notify();
			return true;
		}
	}	
	public synchronized void attesaTurno(String nomeCliente){
		while(!clienteServito.equals(nomeCliente)){
			try {
				wait();
			} catch (InterruptedException e) {	}
		}
	}
	public synchronized void attesaFineTaglio(String nomeCliente){
		while(clienteServito.equals(nomeCliente)){
			try {
				wait();
			} catch (InterruptedException e) {	}
		}
	}
	public synchronized void servito(String cli){
		System.out.println(cli+" esce servito");
		clienteServito="nessuno";
		poltrona=statoPoltrona.AVAILABLE;
		notifyAll();
	}
	
	public synchronized String primoClienteDaServire(){
		String cliente=clientiInAttesa.remove(0);
		if(clientiInAttesa.size()==0){
			coda=statoCoda.EMPTY;
		} else {
			coda=statoCoda.SOMEONE;
		}
		return cliente;
	}
	
	public synchronized void servizioCliente(String cli){
		poltrona=statoPoltrona.OCCUPIED;
		clienteServito=cli;
		System.out.println("Il barbiere chiama "+cli+", che si accomoda in poltrona per il taglio");	
		notifyAll();
	}
}
