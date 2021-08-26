import java.net.*;
import java.io.*;
import java.util.Date;

public class DaytimeClient{

	public static void main(String[] args) throws IOException{
		String str;
		InetAddress addr = InetAddress.getByName(null);
		System.out.println("addr = " +addr);
		Socket con = new Socket(addr, 1333);

		try{
			System.out.println("socket = " + con);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			str = in.readLine();
			System.out.println(str);
		}
		finally{
			System.out.println("closing");
			con.close();
		}

	}

}