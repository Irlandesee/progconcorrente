import java.io.*;
import java.net.*;
import java.util.*;

public class QASlave extends Thread{
	enum QAserverState {WAITFORCLIENT, WAITFORANSWER, WAITFORCONFIRM};
	private ArrayList<String> questions = new ArrayList<String>();
	private ArrayList<String> answers = new ArrayList<String>();
	private int num = 0;
	Socket clientSocket;
	private QAserverState state = QAserverState.WAITFORCLIENT;
	private Random rand = new Random(System.currentTimeMillis());
	BufferedReader is;
	PrintWriter os;
	public QASlave(Socket s, ArrayList<String> q, ArrayList<String> a) {
		questions=q;
		answers=a;
		clientSocket=s;
	}
	public void run() {
		String inLine;
		try {
			is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			os = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);
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
}
