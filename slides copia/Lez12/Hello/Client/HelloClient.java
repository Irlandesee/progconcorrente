import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class HelloClient {
	public static void main(String[] args) {
		try {
			Registry registro = LocateRegistry.getRegistry(1099);
			Hello stub = (Hello) registro.lookup("Hello");
			String response = stub.sayHello();
			System.out.println("response: " + response);
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}
}

