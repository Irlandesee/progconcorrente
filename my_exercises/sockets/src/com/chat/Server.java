import java.net.*;
import java.io.*;

public class Server{

	private static final int SERVER_PORT = 8080;
	public static void main(String[] args){

		ServerSocket ss = new ServerSocket(SERVER_PORT);
		System.out.println("Server started @ port:"+SERVER_PORT);
		try{
			while(true){
				Socket s = ss.accept();
				System.out.println("server accepts a new client");
				try{
					new ServerChatSlave(s);
				}catch(IOException e){
					s.close();
				}
			}
		}finally{
			s.close();
		}
	} 

}