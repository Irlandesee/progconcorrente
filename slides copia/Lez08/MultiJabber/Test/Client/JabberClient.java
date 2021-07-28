import java.io.*;
import java.net.*;

public class JabberClient {
	InetAddress addr;
	Socket socket;
	JabberClient(){
		try {
			addr = InetAddress.getByName(null);
		} catch (UnknownHostException e) {
			System.err.println("JabberClient: could not get IP address");
			System.exit(0);
		}
		System.out.println("addr = " + addr);
	}
	void exec(String myName) {	
		try {
			socket = new Socket(addr, 8080);
			System.out.println("socket = " + socket); 
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			for (int i = 0; i < 10; i++) {
				Thread.sleep(1500);
				out.println("hello " + i +" from "+myName);
				String str = in.readLine();
				System.out.println(str);
			}
			out.println("END");
		} catch (IOException | InterruptedException e) {
			System.err.println("JabberClient: IO problems");
		} 
		System.out.println("closing...");
		try { socket.close(); } catch (IOException e) {}
	}
	public static void main(String[] args) {
		new JabberClient().exec(args.length==0?"anonymous":args[0]);
	}
}
