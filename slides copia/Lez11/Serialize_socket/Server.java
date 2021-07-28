import java.io.*;
import java.net.*;

public class Server {
	public static final int PORT = 9999;
	Employee obj =null;
	void exec() {
		Socket socket=null;
		ServerSocket s=null;
		try {
			s = new ServerSocket(PORT);
			socket = s.accept();
			System.out.println("Connection accepted: " + socket);
			ObjectInputStream obj_in_s =new ObjectInputStream(socket.getInputStream());
			Employee emp = (Employee) obj_in_s.readObject();
			emp.display();
			obj_in_s.close();
			System.out.println("Connection ended");
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("closing...");
			try {
				socket.close();
				s.close();
			} catch (IOException e) { }
		}
	}
	public static void main(String[] args) {
		new Server().exec();
	}
}
