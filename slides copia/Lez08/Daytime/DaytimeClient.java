
import java.net.*;
import java.io.*;
public class DaytimeClient {
	public static void main(String[] args) throws IOException {
		String str;
		InetAddress addr = InetAddress.getByName(null);
		System.out.println("addr = " + addr);
		Socket connection = new Socket(addr, 1333);
		try {
			System.out.println("socket = " + connection);
			BufferedReader in = new BufferedReader(new
					InputStreamReader(connection.getInputStream()));
			str = in.readLine();
			System.out.println(str);
		}
		finally {
			System.out.println("closing...");
			connection.close();
		}
	}
}