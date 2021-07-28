import java.net.*;
import java.io.*;

public class CounterProxy implements CounterInterface_with_close{
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	public CounterProxy() throws Exception {
		InetAddress addr = InetAddress.getByName(null);
		System.out.println("addr = " + addr);
		socket = new Socket(addr, CounterInterface.PORT); // connessione
		// crea gli stream di input/output
		System.out.println("socket = " + socket);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
	}
	// metodi dell'interfaccia ServerInterface 
	public int sum(int s) throws IOException {
		out.println("<sum> "+Integer.toString(s));
		String strResult = in.readLine();
		return Integer.parseInt(strResult);
	}
	public int reset() throws IOException {
		out.println("<reset>");
		String strResult = in.readLine();
		return Integer.parseInt(strResult);
	}
	public int increment() throws IOException {
		out.println("<incr>");
		String strResult = in.readLine();
		return Integer.parseInt(strResult);
	}
	public void close() throws IOException {
		System.out.println("closing...");
		out.println("<end>");
		socket.close();
	}
}
