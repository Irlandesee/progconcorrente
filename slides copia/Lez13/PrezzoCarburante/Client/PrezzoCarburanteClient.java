import java.util.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
public class PrezzoCarburanteClient {
	private void exec(String host) {
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			PrezzoCarburante stub = (PrezzoCarburante) registry.lookup("Prezzi");
			int numDistr = (int)(Math.random()*100);
			for(int tappa=0; tappa<numDistr ; tappa++){
				String distrName = "d"+(int)(Math.random()*10);
				Distributore resp = stub.getInfoDistributore(distrName);
				if(resp==null){
					System.out.println(distrName + " sconoscuto: aggiungo!");
					stub.addDistributore(new Distributore(distrName, Math.random()+1, Math.random()+1));
				} else { // aggiorno i prezzi
					System.out.println("prezzi vecchi: " + resp);
					stub.setNewPrezzi(new Distributore(distrName,Math.random() + 1, Math.random()+1));
					Distributore distribNew = stub.getInfoDistributore(distrName);
					System.out.println("prezzi nuovi: " + distribNew);
				}
				List<String> list = stub.getNomiDistributori();
				System.out.println("Num distrib. salvati: " + list.size());
				// viaggio ancora per poi rifermarmi ad un nuovo distributore
				Thread.sleep((long) (Math.random()*1000));
			}
		} catch (Exception e) {
			System.err.println("Client exc: " + e.toString());
		}
	}
	public static void main(String[] args) {
		new PrezzoCarburanteClient().exec((args.length < 1) ? "localhost" : args[0]);
	}
}
