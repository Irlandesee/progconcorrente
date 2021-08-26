import java.net.*;
import java.io.*;
import java.util.Date;

public class DaytimeServer{

	public static final int DayTime_PORT = 1333;

	public static void main(String[] args){
		try{
			ServerSocket server = new ServerSocket(DayTime_PORT);
			Socket con = null;
			while(true){
				try{
					con = server.accept();
					System.out.println("Connessione accettata");
					Writer out = new OutputStreamWriter(con.getOutputStream());
					Date now = new Date();
					out.write(now.toString() + "\r\n"); out.flush();
					con.close();
				}catch(IOException e){ }
				finally{
					try{
						if(con != null) con.close();

					}catch(IOException e){ }
				}
			}
		}catch(IOException e){ }
	} 


}
