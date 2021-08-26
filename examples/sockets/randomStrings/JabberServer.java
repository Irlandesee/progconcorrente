import java.io.*;
import java.net.*;


public class JabberServer{

	public static final int PORT = 8080;

	public static void main(String[] args) throws IOException{
		ServerSocket ss = new ServerSocket(PORT);

		System.out.println("Started: " +ss);
		try{
			Socket s = ss.accept();
			try{
				System.out.println("Connection accepted: " +s);
				BufferedReader in = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
				PrintWriter out = new PrintWriter(new BufferedWriter(
					new OutputStreamWriter(s.getOutputStream())), true);
				while(true){
					String str = in.readLine();
					if(str.equals("end")) break;
					System.out.println("Echo: "+str);
					out.println(str);
				}
			}finally{
				System.out.println("closing...");
				s.close();
			}
		}finally{
			ss.close();
		}

	} 

}