import java.io.*;
import java.net.*;
import java.util.*;

public class QAServer {
	public static final int PORTNUM = 1234;
	private ArrayList<String> questions = new ArrayList<String>();
	private ArrayList<String> answers = new ArrayList<String>();
	private ServerSocket serverSocket;
	private int numQuestions;
	private int num = 0;
	private File qaFile;
	public QAServer(String fileName) {
		try {
			serverSocket = new ServerSocket(PORTNUM);
			System.out.println("Server up and running...");
		} catch (IOException e) {
			System.err.println("Exception: couldn't create socket");
			System.exit(1);
		}
		qaFile = new File(fileName);
		if(!qaFile.exists()) {
			System.err.println("Error: "+ fileName+" doesn't exist!");
			System.exit(1);
		}
		if (!initQnA()) {
			System.err.println("Error: couldn't initialize Q&A");
			System.exit(1);
		}
	}
	private boolean initQnA() {
		BufferedReader br=null;;
		try {
			br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(qaFile))));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				// System.out.println(strLine);
				questions.add(strLine);
				if ((strLine = br.readLine()) != null)
					answers.add(strLine);
				numQuestions++;
			}
		} catch (IOException e) {
			System.err.println("I/O error trying to read questions");
			return false;
		} finally {
			try {	br.close(); } catch (Exception e) {	}
		}
		return true;
	}
	public void exec() {
		Socket clientSocket = null;
		while (true) {
			if (serverSocket == null)
				return;
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e) { System.exit(1); }
			new QASlave(clientSocket, questions, answers).start();
		}
	}
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java QAServer <QA file name>");
			return;
		} else {
			new QAServer(args[0]).exec();
		}
	}
}
