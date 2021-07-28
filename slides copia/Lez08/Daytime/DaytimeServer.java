import java.io.*;
import java.net.*;
import java.util.*;

public class DaytimeServer {
	public final static int DayTime_PORT= 1333;
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(DayTime_PORT);
			Socket connection = null;
			while (true) {
				try {
					connection = server.accept();
					Writer out =
							new OutputStreamWriter(connection.getOutputStream());
					Date now = new Date();
					out.write(now.toString() +"\r\n");
					out.flush();
					connection.close();
				}
				catch (IOException ex) {}
				finally {
					try {
						if (connection != null) connection.close();
					} catch (IOException ex) {}
				}
			}
		} catch (IOException ex) {}
	}
}

