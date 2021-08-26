import java.net.*;
import java.io.*;

public class ServeUnJabber extends Thread{
	private Socket s;
	private BufferedReader in;
	private PrintWriter out;

	public ServeUnJabber(Socket s) throws IOException{
		this.s = s;
		in = new BufferedReader(
			new InputStreamReader(s.getInputStream()));
		out = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(s.getOutputStream())), true); // true for auto-flash
		start();
	}

	public void run(){
		try{
			while(true){
				String str = in.readLine();
				if(str.equals("END")) break;
					System.out.println("ECHO: " +str);
					out.println(str);
				}
				System.out.println("closing...");
		}catch(IOException e){
			System.err.println("IOException");
		}finally{
			try{
				s.close();
			}catch(IOException e){
				System.err.println("socket not closed");
			}
		}
	}
}