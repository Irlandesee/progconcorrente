import java.net.*;
import java.io.*;

public class HttpClient{

	void exec(String urlStr){
		URI uri = null;
		Socket s = null;
		try{
			uri = new URI(urlStr);
		}catch(URISyntaxException e1){
			System.err.println("invalid url");
			System.exit(0);
		}
		try{
			String host = uri.toURL().getHost();
			String path = uri.getRawPath();
			System.out.printf("Host: %s\npath: %s\n", host, path);
			s = new Socket(host, 80);
			BufferedReader in = new BufferedReader(
				new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(s.getOutputStream());

			out.print( "GET " + path + " HTTP/1.1\r\n"
				+"Host: "+host+"\r\n"
				+"Connection: close\r\n\r\n");
			out.flush( );

			//Response
			String messageString = " ";
			String line = "";
			System.out.println("Message from " +host+ ":\n");
			while((line = in.readLine()) != null){
				System.out.println(line);
				if(line.equals("")){
					System.out.println("<fine header>");
					break;
				}
			}
		}catch(IOException e){
			System.err.println("I/O problems: terminating");
			e.printStackTrace();
			System.exit(0);
		}finally{
			System.out.println("*** closing");
			try{s.close();}catch(IOException e){ }
		}
	}

	public static void main(String[] args){
		new HttpClient().exec(args[0]);
	}

}