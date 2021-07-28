import java.io.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class TTYchatClientImpl extends UnicastRemoteObject implements TTYchatClient {
	private static final long serialVersionUID = 1L;
	String my_name;
	public TTYchatClientImpl(String n) throws RemoteException {
		my_name = n;
	}
	public String name() throws RemoteException {
		return my_name;
	}
	public void somethingSaid(String who_what) throws RemoteException {
		System.out.println(who_what); 
	}
	public static void main(String args[]) {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("What is your name?");
			TTYchatClientImpl me = new TTYchatClientImpl(input.readLine());
			Registry reg = LocateRegistry.getRegistry();
			TTYchat server = (TTYchat) reg.lookup("TTYCHAT");
			server.enterRoom(me);
			System.out.println("You can now chat in the room");
			while (true) {
				String s = input.readLine();
				if(s.equals("<quit>")) {
					server.exitRoom(me);
					break;
				} else {
					server.saySomething(s, me);
				}
			}
			UnicastRemoteObject.unexportObject(me, false);
		} catch (Exception e) {
			System.out.println("Client err:"+e.getMessage());
		}
	}
}


