import java.net.*;
import java.util.*;
import java.io.*;

public class CounterSkeleton extends Thread {
	private Socket theSocket;
	private BufferedReader istream;
	private PrintWriter ostream;
	private Counter server = new Counter();
	public CounterSkeleton(Socket socket) {
		theSocket = socket;
	}
	public void run() {
		try {
			istream = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
			ostream = new PrintWriter(new BufferedWriter(new
					OutputStreamWriter(theSocket.getOutputStream())), true);
			while (!theSocket.isClosed()) {
				int result = 0;
				String myOper = istream.readLine();
				if (myOper.equals("<incr>"))
					result = server.increment();
				else if (myOper.equals("<reset>"))
					result = server.reset();
				else if (myOper.startsWith("<sum>")) {
					StringTokenizer st = new StringTokenizer(myOper);
					String op = st.nextToken(); String add = st.nextToken();
					result = server.sum(Integer.parseInt(add));
				} else if (myOper.startsWith("<end>")) {
					theSocket.close();
				} else {
					System.out.println("operation not recognized: "+myOper);
				}
				ostream.println(result);
			}
		} catch (Exception e) {	}  
	}
}
