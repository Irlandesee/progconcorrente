import java.io.*;
import java.net.*;

public class QAClient {
	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;
	QAClient(String address){
		try {
			socket = new Socket(address, QAServer.PORTNUM);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		} catch (IOException e) {
			System.err.println("Couldn't create stream socket");
			System.exit(1);
		}
	}
	void exec() {
		StringBuffer userAnswer = new StringBuffer(128);
		String serverQuestion;
		int c;
		// domande e risposte con il server
		try {
			while ((serverQuestion = in.readLine()) != null) {
				System.out.println("Server: " + serverQuestion);
				if (serverQuestion.equals("END"))
					break;
				while ((c = System.in.read()) != '\n')
					userAnswer.append((char) c);
				System.out.println("Client: " + userAnswer);
				out.println(userAnswer.toString());
				userAnswer.setLength(0);
			}
			out.close(); in.close(); socket.close();
		} catch (IOException e) {
			System.err.println("I/O error trying to talk to server");
		}
	}
	public static void main(String[] args) {
		if (args.length != 1) {
			new QAClient("localhost").exec();
		} else
			new QAClient(args[0]).exec();
	}
}