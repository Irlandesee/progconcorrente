import java.net.*;
import java.io.*;

public class MultiJabberServer{

	private static final int port = 8080;

	public static void main(String[] args) throws Exception{
		ServerSocket ss = new ServerSocket(port);
		System.out.printf("[SERVER STARTE AT PORT] : [%d]\n", port);
		try{
			while(true){
				Socket s = ss.accept();
				try{
					new ServeUnJabber(s);
				}catch(IOException e){
					//Se fallisce chiude il socket
					//altrimenti lo chiude il thread
					s.close();
				} 
			}
		}finally{
			ss.close();
		}

	}

}