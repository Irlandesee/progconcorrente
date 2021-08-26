import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class QAServer extends Thread{
	public static final int PORTNUM = 1234;
	public enum QAserverState {
		WAITFORCLIENT,
		WAITFORANSWER,
		WAITFORCONFIRM };

	private ArrayList<String> questions = new ArrayList<String>();
	private ArrayList<String> answers = new ArrayList<String>();

	private ServerSocket ss;
	private int numQuestions;
	private int num;

	private QAserverState state = QAserverState.WAITFORCLIENT;

	private File qaf; //questions answers file
	private Random rand = new Random(System.currentTimeMillis());


	public QAServer(String fileName){
		try{
			ss = new ServerSocket(PORTNUM);
			System.out.println("Server up and running at port: "+PORTNUM);
		}catch(IOException e){
			System.err.println("Exception, could not create socket");
			System.exit(1);
		}
		qaf = new File(fileName);
		if(!qaf.exists()){
			System.err.println("Error: "+fileName+"does not exists!");
			System.exit(1);
		}
		if(!initQnA()){
			System.err.println("Error: could not init Q&A");
			System.exit(1);
		}

	}

	private boolean initQnA(){
		BufferedReader br = null;
		try{
			br = new BufferedReader(new InputStreamReader(
				new DataInputStream(
					new FileInputStream(qaf))));
			String line;
			while((line = br.readLine()) != null){
				questions.add(line);
				if((line = br.readLine()) != null)
					answers.add(line);
				numQuestions++;
			}
		}catch(IOException e){
			System.err.println("I/O error while trying to read questions");
			return false;
		}finally{
			try{br.close();}catch(Exception e){ }
		}
		return true;
	}

	public void run(){
		Socket cs = null;
		while(true){
			if(ss == null)
				return;
			try{
				cs = ss.accept();
			}catch(IOException e){System.exit(1);}
			try{
				BufferedReader is = new BufferedReader(
					new InputStreamReader(cs.getInputStream()));

				PrintWriter os = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(
						cs.getOutputStream())), true);
				String inLine;
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
		}
		return outStr;
	}

	public static void main(String[] args){
		if(args.length != 1){
			System.out.println("Usage: java QAServer <QA file name>");
			return;
		}else{
			new QAServer(args[0]).start();
		}


	}

}