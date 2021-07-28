import java.io.*;
import java.net.*;

public class CounterServer {
	// public static final int PORT=8888;
	Counter theCounter;
	CounterServer(){
		theCounter=new Counter();
	}

	void exec() {
		ServerSocket serverSocket;
		Socket clientSocket;
		try {
			serverSocket = new ServerSocket(CounterInterface.PORT);
			System.out.println("Started: " + serverSocket);
			while (true) {
				System.out.println("Master server waiting a connection...");
				clientSocket = serverSocket.accept();
				System.out.println("Master server connected with "+clientSocket);
				new CounterSkeleton(clientSocket, theCounter).start();
			}
		} catch (IOException e) {
			System.err.println();
		}
	}
	public static void main(String[] args) {
		new CounterServer().exec();
	}
}
