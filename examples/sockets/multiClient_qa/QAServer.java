import java.net.*;
import java.io.*;
import java.util.*;

public class QAServer{

	public static final int PORTNUM = 1234;

	private ArrayList<String> questions = new ArrayList<String>();
	private ArrayList<String> answers = new ArrayList<String>();

	private ServerSocket ss;

	private int numQuestions;
	private int num;
	private File qaf;


	public QAServer(String filename){
		try{
			ss = new ServerSocket(PORTNUM);
			System.out.println("Server up and running...");
		}catch(IOException e){
			System.err.println("Exception: could not create socket");
			System.exit(1);
		}
		qaf = new File(filename);
		if(!qaf.exists()){
			System.err.println("Error: " +filename+ "does not exists");
			System.exit(1);
		}
		if(!initQnA()){
			System.err.println("Error: could not initialize Q&A");
			System.exit(1);
		}
	}

	private boolean initQnA(){
		BufferedReader br = null;
		try{
			br = new BufferedReader(new InputStreamReader(
				new DataInputStream(new FileInputStream(qaf))));
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
			try{br.close();} catch(Exception e){ } 
		}
		return true;
	}

	public void exec(){
		Socket cs = null;
		while(true){
			if(ss == null)
				return;
			try{
				cs = ss.accept();
			}catch(IOException e){System.exit(1);}
			new QASlave(cs, questions, answers).start();
		}
	}

	public static void main(String[] args){
		if(args.length != 1){
			System.out.println("Usage java QAServer <Qa file name>");
			return;
		}else{
			new QAServer(args[0]).exec();
		}
	}


}