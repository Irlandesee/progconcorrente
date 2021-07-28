import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class TTYchatServer {
	static public void main(String args[]) {
		try {
			TTYchatImpl obj = new TTYchatImpl();
			Registry registro = LocateRegistry.createRegistry(1099);
			registro.rebind("TTYCHAT", obj);
			System.out.println("TTYChat Server bound in registry");
		} catch (Exception e) {
			System.out.println("TTYChatServer err: " + e.getMessage());
		}
	}
}

