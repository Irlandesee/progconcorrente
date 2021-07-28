import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
 
public class HelloPersonImpl extends UnicastRemoteObject implements HelloPerson { 
    private static final long serialVersionUID = 1L;
    public HelloPersonImpl() throws RemoteException {
       super();
    }
    public String sayHello() throws RemoteException {
        return "Hello, world!";
    }
    public String sayHello(Person p) { 
        return "Hello, " + p.getName();
    }
    public static void main(String args[]) {
        try {
            HelloPersonImpl obj = new HelloPersonImpl();
            Registry registro = LocateRegistry.createRegistry(1099);
            // Registry registro = LocateRegistry.getRegistry();
            registro.rebind("HelloPerson", obj);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
