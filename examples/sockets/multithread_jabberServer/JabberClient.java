import java.io.*;
import java.net.*;

public class JabberClient{
	private InetAddress addr;
	private Socket s;

	public JabberClient(){
		try{
			addr = InetAddress.getByName(null);
		}catch(UnknownHostException e){
			System.err.println("JabberClient: no IP address");
			System.exit(0);
		}
		System.out.println("addr = " +addr.toString());
	}

	public static void main(String[] args){
		new JabberClient().exec(args.length==0?"anonymous":args[0]);
	}

	private void exec(String myName){
		try{
			s = new Socket(addr, 8080);
			System.out.println("Socket = "+s);
			BufferedReader in = new BufferedReader(
				new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(
				new BufferedWriter(new OutputStreamWriter(
					s.getOutputStream())), true);
			for(int i = 0; i < 10; i++){
				Thread.sleep(1500);
				out.println("hello " + i + "from: " +myName);
				String str = in.readLine();
				System.out.println(str);
			}
			out.println("END");
		}catch(IOException | InterruptedException e){
			System.err.println("Jabber client error: IO problems");
		}
		System.out.println("closing...");
		try{s.close();}catch(IOException e){}
	}

}