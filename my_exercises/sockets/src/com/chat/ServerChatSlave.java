import java.net.*;
import java.io.*;

public class SegmentServer extends Thread{


	private Socket s;
	private BufferedReader inStream;
	private PrintWriter outStream;

	private final String QUIT = ":q";
	private final String SEND = ":y";
	private final String CANCEL = ":c";
	private final String CLEAR = ":pC";

	public ServerChatSlave(Socket _s) throws IOException{
		s = _s;
		inStream = new BufferedReader(
			new InputStreamReader(s.getInputStream()));
		outStream = new PrintWriter(
			new BufferedWriter(
				new OutputStreamWriter(s.getOutputStream())), true);
		start();
	}

	public void run(){
		try{
			while(true){
				printHelp();
				String str = in.readLine();
				if(str.equalsIgnoreCase(QUIT)){
					System.out.println("Client has disconnected");
					break;
				}
				System.out.println("Client > " +str);
				StringBuffer buf = new StringBuffer(128);
				int c;
				while((c = System.in.readChar()) != '\n')
					buf.append((char) c);
				String outString = buf.toString();
				buf.setLength(0);
				System.out.println(outString + "send? ");
				String choice = System.in.readLine();
				switch(choice){
					case SEND:
						out.println(outString),
						System.out.println("Message sent");
						break;
					case CANCEL:
						System.out.println("Message not sent");
						break;
				}
			}
		}
	}

	private void printHelp(){
		out.println("discconect from chat: "+QUIT);
		out.println("send message: "+SEND);
		out.println("cancel: "+CANCEL);
		out.pritln("clear the screen "+CLEAR);
	}

}