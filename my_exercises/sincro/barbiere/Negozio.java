import java.util.ArrayList;
import java.util.List;

public class Negozio{

	private final int NUM_SEDIE = 3;
	private enum Statocoda {EMPTY, FULL, SOMEONE};
	private enum Statopoltrona {AVAILABLE, OCCUPIED};
	private String clienteServito;
	private List<String> clientiInAttesa = new ArrayList<String>();
	private Statocoda coda;
	private Statopoltrona poltrona;

	public Negozio(){
		coda = Statocoda.EMPTY;
		poltrona = Statopoltrona.AVAILABLE;
		clienteServito = "nessuno";
	}

	public synchronized boolean ciSonoClientiInAttesa(){
		return coda != Statocoda.EMPTY;
	}

	public synchronized void attesaDormiente(){
		System.out.println("Il barbiere va a dormire");
		while(coda == Statocoda.EMPTY){
			try{
				wait();
			}catch(InterruptedException ie){}
		}
		System.out.println("Il barbiere si sveglia");
	}

	public synchronized boolean possoEntrare(String nomeCliente){
		System.out.println(nomeCliente+ " entra nel negozio");
		if(coda == Statocoda.FULL){
			System.out.println(nomeCliente+" esce perchè non ce posto");
			return false;
		}else{
			System.out.println(nomeCliente+" e si mette in coda");
			clientiInAttesa.add(nomeCliente);
			if(clientiInAttesa.size() == NUM_SEDIE)
				coda = Statocoda.FULL;
			else
				coda = Statocoda.SOMEONE;
			notify();
			return true;
		}
	}

	public synchronized void attesaTurno(String nomeCliente){
		while(!clienteServito.equals(nomeCliente)){
			try{
				wait();
			}catch(InterruptedException ie){ }
		}
	}

	public synchronized void attesaFineTaglio(String nomeCliente){
		while(clienteServito.equals(nomeCliente)){
			try{
				wait();	
			}catch(InterruptedException ie){ }
		}
	}

	public synchronized void servito(String nomeCliente){
		System.out.println(nomeCliente+" esce servito");
		clienteServito = "nessuno";
		poltrona = Statopoltrona.AVAILABLE;
		notifyAll();
	}


	public synchronized String primoClienteDaServire(){
		String cliente = clientiInAttesa.remove(0);
		if(clientiInAttesa.size() == 0)
			coda = Statocoda.EMPTY;
		else
			coda = Statocoda.SOMEONE;
		return cliente;
	}

	public synchronized void servizioCliente(String nomeCliente){
		poltrona = Statopoltrona.OCCUPIED;
		clienteServito = nomeCliente;
		System.out.println("Il barbiere chiama "+nomeCliente+ "e inizia il taglio");
		notifyAll();
	}

}