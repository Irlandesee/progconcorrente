import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;


public class HelloPersonClient{
	public static void main(String[] args){
		try{
			Registry reg = LocateRegistry.getRegistry(1099);
			HelloPerson stub = (HelloPerson) reg.lookup("HelloPerson");
			String response = stub.sayHello();
			System.out.println("response: "+response);

			Person someone = new Person("Mattia Lunardi");
			response = stub.sayHello(someone);
			System.out.println("response: "+response);
		}catch(Exception e){
			System.err.println("Client exception: "+e.toString());
			e.printStackTrace();
		}
	}
}