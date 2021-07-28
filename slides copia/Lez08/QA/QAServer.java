import java.io.*;
import java.net.*;
import java.util.*;

public class QAServer extends Thread{
	public static final int PORTNUM = 1234;
	enum QAserverState {WAITFORCLIENT, WAITFORANSWER, WAITFORCONFIRM};
	private ArrayList<String> questions = new ArrayList<String>();
	private ArrayList<String> answers = new ArrayList<String>();
	private ServerSocket serverSocket;
	private int numQuestions;
	private int num = 0;
	private QAserverState state = QAserverState.WAITFORCLIENT;
	private File qaFile;
	private Random rand = new Random(System.currentTimeMillis());
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
	public void run() {
		Socket clientSocket = null;
		while (true) {
			if (serverSocket == null)
				return;
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e) { System.exit(1); }
			try {
				BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter os = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
				String inLine;
				os.println(processInput(null));
				while ((inLine = is.readLine()) != null) {
					String outLine = processInput(inLine);
					os.println(outLine);
					if (outLine.equals("END"))
						break;
				}
				os.close(); is.close(); clientSocket.close();
			} catch (Exception e) { System.err.println("Exception "+e);}
		}
	}
	String processInput(String inStr) {
		String outStr = "";
		switch (state) {
		case WAITFORCLIENT:  // Ask a question
			outStr = questions.get(num);
			state = QAserverState.WAITFORANSWER;
			break;
		case WAITFORANSWER:	// Check the answer
			if (inStr.equalsIgnoreCase(answers.get(num)))
				outStr = "That's correct! Want another? (y/n)";
			else
				outStr = "Wrong, the correct answer is " + answers.get(num) + ". Want another? (y/n)";
			state = QAserverState.WAITFORCONFIRM;
			break;
		case WAITFORCONFIRM:	// See if they want another question
			if (inStr.equalsIgnoreCase("y")) {
				num = Math.abs(rand.nextInt()) % questions.size();
				outStr = questions.get(num);
				state = QAserverState.WAITFORANSWER;
			} else {
				outStr = "END";
				state = QAserverState.WAITFORCLIENT;
			}
			break;
		}
		return outStr;
	}
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java QAServer <QA file name>");
			return;
		} else {
			new QAServer(args[0]).start();
		}
	}
}
