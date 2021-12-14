import java.io.*;
import java.net.*;

public class ClientAsta {
	public static void main(String[] args) throws IOException, InterruptedException {
		final int numClients=4; 
		InetAddress addr = InetAddress.getByName(null);
		for(int i=0; i<numClients; i++){
			String cliName = "cli"+i;
			new ClienteAstaThread(addr, ServerAsta.PORT, cliName);
		}
	}
}
