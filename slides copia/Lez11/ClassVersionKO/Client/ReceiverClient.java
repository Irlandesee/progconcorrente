import java.io.*;
import java.net.*;

public class ReceiverClient {
	public static void main(String[] args) throws IOException {
		InetAddress addr = InetAddress.getByName(null);
		Socket socket = new Socket(addr, 9996);
		try {
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Point p = (Point) ois.readObject();
			System.out.print("Object is: " + p); 
		} catch(InvalidClassException e){
			System.out.println("Object class invalid");
		} catch(ClassNotFoundException e){
			System.out.println("Object class non found");
		} 
		finally {
			socket.close();
		}
	}
}
