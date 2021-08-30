package chat;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;
import java.util.Scanner;

public class ClientSlave extends Thread{

	private Socket s;
	private BufferedReader inStream;
	private PrintWriter outStream;
	private Scanner sc;

	private static int threadCount = 0;
	private int id;

	private final int SERVER_PORT = 8080;

	private final String QUIT = ":q";
	private final String SEND = ":y";
	private final String CANCEL = ":c";
	private final String CLEAR = ":pC";
	private final String HELP = ":pH";

	public static int getThreadCount(){
		return threadCount;
	}

	public ClientSlave(InetAddress addr, int _id){
		System.out.println("Creating new client: "+ _id);
		id = _id;
		threadCount++;
		//apertura della connessione
		try{
			s = new Socket(addr, SERVER_PORT);
		}catch(IOException e){
			System.out.println("Socket creation failed");
		}
		//apertura degli stream di input e output
		try{
			inStream = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
			outStream = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(s.getOutputStream())), true);
			sc = new Scanner(System.in);
			
		}catch(IOException e){
			try{//se la creazione degli stream io fallisce -> chiudo socket
				threadCount--;
				s.close();
			}catch(IOException e2){System.err.println("Socket not closed");}			
		}
		start();
	}

	public void run(){
		String inString = "";
		String typedString = "";
		StringBuffer buf;
		try{
			while(true){
				inString = inStream.readLine();
				System.out.println("Server > "+inString);
				buf = new StringBuffer(128);
				int c;
				while((c = System.in.read()) != '\n')
					buf.append((char) c);
				typedString = buf.toString();
				buf.setLength(0);
				if(typedString.equals(QUIT)){
					System.out.printf("\nClient %d disconnecting from server", Thread.currentThread().getId());
					break;
				}
				System.out.println(typedString + "\nSend? :y,:c");
				String choice = sc.nextLine();
				switch(choice){
					case SEND:
						outStream.println(typedString);
						System.out.println("Message sent successfully");
						break;
					case CANCEL:
						typedString = "";
						System.out.println("Message not sent");
						break;
					case CLEAR:
						clearScreen();
						break;
					case HELP:
						printHelp();
						break;
				}

			}
		}catch(IOException e){
			System.out.printf("Client slave %d IOException", id);
			e.printStackTrace();
		}
	}

	private void printHelp(){
		System.out.println("discconect from chat: "+QUIT);
		System.out.println("send message: "+SEND);
		System.out.println("cancel: "+CANCEL);
		System.out.println("clear the screen: "+CLEAR);
		System.out.println("print help: "+HELP);
	}

	private void clearScreen(){
		System.out.println("Clearing the screen");
		for(int i = 0; i < 100; i++)
			System.out.println("");
		System.out.println("done");
	}

}