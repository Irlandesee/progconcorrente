import java.io.*;
import java.net.*;

public class Client {
	Employee empl;
	Client(){
		empl=new Employee("Emerenziano Paronzini", 123, 3000);
	}
	void exec() {
		Socket socket=null;
		InetAddress addr=null;
		System.out.println("Client starts");
		try {
			addr = InetAddress.getByName(null);
			socket = new Socket(addr, Server.PORT);
			ObjectOutputStream obj_out_s = new ObjectOutputStream(socket.getOutputStream());
			empl.display();
			obj_out_s.writeObject(empl);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("closing...");
			try {
				socket.close();
			} catch (IOException e) { }
		}
		System.out.println("Client finished");
	}
	public static void main(String[] args) {
		new Client().exec();
	}
}
