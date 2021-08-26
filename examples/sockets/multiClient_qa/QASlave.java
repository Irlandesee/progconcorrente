import java.io.*;
import java.net.*;
import java.util.*;

public class QASlave extends Thread{

	public enum QAserverState{
		WAITFORCLIENT,
		WAITFORANSWER,
		WAITFORCONFIRM
	};

	private ArrayList<String> questions = new ArrayList<String>();
	private ArrayList<String> answers = new ArrayList<String>();

	private int num = 0;

	private Socket cs;

	private QAserverState state = QAserverState.WAITFORCLIENT;
	
	private Random rand = new Random(System.currentTimeMillis());

	private BufferedReader is;
	private PrintWriter os;

	public QASlave(Socket s, ArrayList<String> q, ArrayList<String> a){
		questions = q;
		answers = a;
		cs = s;
	}

	private String processInput(String inStr){
		String outStr = "";
		switch(state){
			case WAITFORCLIENT: //ask a question
				outStr = questions.get(num);
				state = QAserverState.WAITFORANSWER;
				break; 
			case WAITFORANSWER:
				if(inStr.equalsIgnoreCase(answers.get(num)))
					outStr = "correct! want another? (y/n)";
				else{
					outStr = "Wrong, correnct asnwer: " +
						answers.get(num) + ". Want another? (y/n)";
				}
				state = QAserverState.WAITFORCONFIRM;
				break;
			case WAITFORCONFIRM:
				if(inStr.equalsIgnoreCase("y")){
					num = Math.abs(rand.nextInt()) % questions.size();
					outStr = questions.get(num);
					state = QAserverState.WAITFORANSWER;
				}else{
					outStr = "END";
					state = QAserverState.WAITFORCLIENT;
				}
				break;
		}
		return outStr;
	}

	public void run(){
		String inLine = "";
		try{
			is = new BufferedReader(
				new InputStreamReader(cs.getInputStream()));
			os = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(cs.getOutputStream())), true);
			os.println(processInput(null));
			while((inLine = is.readLine()) != null){
				String outLine = processInput(inLine);
				os.println(outLine);
				if(outLine.equals("END"))
					break;
			}
			os.close();
			is.close();
			cs.close();
		}catch(Exception e){
			System.err.println("Exception: "+e);
			e.printStackTrace();
		}

	}

}