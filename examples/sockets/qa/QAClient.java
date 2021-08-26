import java.net.*;
import java.io.*;


public class QAClient{

	private Socket s = null;
	private BufferedReader in = null;
	private PrintWriter out = null;

	public QAClient(String addr){
		try{
			s = new Socket(addr, QAServer.PORTNUM);
			in = new BufferedReader(
				new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(s.getOutputStream())), true);
		}catch(IOException e){
			System.err.println("Couldn't create stream socket");
			System.exit(1);
		}
	}

	public static void main(String[] args){
		if(args.length != 1)
			new QAClient("localhost").exec();
		else
			new QAClient(args[0]).exec();
	}

	private void exec(){
		StringBuffer userAnswer = new StringBuffer(128);
		String serverQuestion;
		int c;
		try{
			while((serverQuestion = in.readLine()) != null){
				System.out.println("Server: "+serverQuestion);
				if(serverQuestion.equals("END"))
					break;
				while((c = System.in.read()) != '\n')
					userAnswer.append((char) c);
				System.out.println("Client: "+userAnswer);
				out.println(userAnswer.toString());
				userAnswer.setLength(0);
			}
			out.close();
			in.close();
			s.close();
		}catch(IOException e){
			System.err.println("I/O error while trying to talk to the server");

		}
	}

}