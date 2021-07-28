import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
 
public class HelloImpl extends UnicastRemoteObject implements Hello { 
    private static final long serialVersionUID = 1L;
    public HelloImpl() throws RemoteException {
       super();
    }
    public String sayHello() throws RemoteException {
        return "Hello, world!";
    }
    public static void main(String args[]) {
        try {
            HelloImpl obj = new HelloImpl();
            Registry registro = LocateRegistry.getRegistry();
            registro.rebind("Hello", obj);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
