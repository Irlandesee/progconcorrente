public class Richiedente extends Thread {
	private int numPosti;
	private AssegnatorePosto assegnatore;
	public Richiedente(String nome, int n, AssegnatorePosto a){
		super(nome);
		this.numPosti=n;
		this.assegnatore=a;
	}
	public void run(){
		System.out.println(getName()+" richiede "+numPosti);
		if(assegnatore.assegnaPosti(getName(), numPosti)){
			System.out.println(getName()+" ottiene "+numPosti);
		} else {
			System.out.println(getName()+" NON ottiene "+ numPosti);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		AssegnatorePosto assegnatore = new AssegnatorePosto();
		Richiedente client1 = new Richiedente("cliente 1", 3, assegnatore);
		Richiedente client2 = new Richiedente("cliente 2", 5, assegnatore);
		Richiedente client3 = new Richiedente("cliente 3", 3, assegnatore);
		Richiedente client4 = new Richiedente("cliente 4", 10, assegnatore);
		client1.start();
		client2.start();
		client3.start();
		client4.start();
		System.out.println("Tutti i clienti sono attivi");		
		client1.join();
		client2.join();
		client3.join();
		client4.join();
		System.out.println("Al termine restano disponibili "+assegnatore.getPostiDisponibili() +" posti");
	}
}
