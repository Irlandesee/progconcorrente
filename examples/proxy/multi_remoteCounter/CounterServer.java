import java.net.*;
import java.io.*;

public class CounterServer{

	public static void main(String[] args) throws IOException{
		ServerSocket ss = new ServerSocket(ServerInterface.PORT);
		System.out.println("Started: "+ss.toString());
		while(true){
			System.out.println("Server: waiting a connection");
			Socket s = ss.accept();
			System.out.println("Server: new client connected "+s.toString());
			new Thread(new CounterServerBis(s)).start();
		}
	}

}