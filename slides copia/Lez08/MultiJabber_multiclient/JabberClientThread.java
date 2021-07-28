import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class JabberClientThread extends Thread {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private static int counter = 0;      // client totali
	private int id = counter++;
	private static int threadcount = 0;  // client correnti

	public static int threadCount() {
		return threadcount;
	}
	public static int threadTotalCount() {
		return counter;
	}

	public JabberClientThread(InetAddress addr) {
		System.out.println("Making client " + id);
		threadcount++;
		try {
			socket = new Socket(addr, MultiJabberServer.PORT);
		} catch (IOException e){System.err.println("Socket failed");}
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			start();
		} catch (IOException e) {
			System.err.println("Client thread "+id+" failure");
			try {
				socket.close();
			} catch (IOException e2) {
				System.err.println("Socket not closed");
			}
		}
	}
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				out.println("Client " + id + ": " + i);
				try {
					Thread.sleep(ThreadLocalRandom.current().nextInt(200,800));
				} catch (InterruptedException e) {	}
				String str = in.readLine();
				System.out.println(str);
			}
			out.println("END");
		} catch (IOException e) {
			System.err.println("IO Exception");
		} finally { // Always close it:
			try { 
				socket.close();
			} catch (IOException e) {
				System.err.println("Socket not closed");
			}
			threadcount--; // Ending this thread
		}
	}
}
