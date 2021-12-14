package es1.client;

import es1.serverinterface.ServerInterface;
import es1.point.Point;
import es1.segment.Segment;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Scanner;

public class ClientProxy extends Thread implements ServerInterface{

	private Socket sock;
	private int proxyID;

	private ObjectInputStream inStream;
	private ObjectOutputStream outStream;

	public ClientProxy(int proxyID, Socket sock){
		this.proxyID = proxyID;
		this.sock = sock;
	}

	public void run(){
		String userCommand = "";
		while(!(userCommand = readUserCommand()).equals(ServerInterface.END_STRING)){
			try{
				switch(userCommand){
					case ServerInterface.NEW_SEGMENT:
						System.out.println("Inserisci coordinate per punto p1");
						Point p1 = readUserPoint();
						System.out.println("Inserisci coordinate per punto p2");
						Point p2 = readUserPoint();
						newSegment(p1, p2);
						break;
					case ServerInterface.SYMMETRICAL:
						System.out.println("Inserisci coordinate punto: ");
						Point p = readUserPoint();
						symmetrical(p);
						break;		
					case ServerInterface.GET_LIST:
						System.out.println("Recupero lista");
						list();
						break;
				}
			}catch(IOException | ClassNotFoundException e){e.printStackTrace();}
		}
		//quit
		System.out.println("Terminating");
		try{
			end();	
		}catch(IOException e1){e1.printStackTrace();}
	}

	private String readUserCommand(){
		Scanner sc = new Scanner(System.in);
		String command = "";
		while(!(command = sc.nextLine()).equals(null)){
			switch(command){
				case ServerInterface.NEW_SEGMENT:
					return ServerInterface.NEW_SEGMENT;
				case ServerInterface.SYMMETRICAL:
					return ServerInterface.SYMMETRICAL;
				case ServerInterface.GET_LIST:
					return ServerInterface.GET_LIST;
				case ServerInterface.END_STRING:
					return ServerInterface.END_STRING;
				default:
					System.out.println("Comando non riconosciuto");
					break;
			}
		}
		sc.close();
		return "";
	}

	private Point readUserPoint(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Inserisci coordinata x: ");
		int x = Integer.parseInt(sc.nextLine());
		System.out.print("Inserisci coordinata y: ");
		int y = Integer.parseInt(sc.nextLine());
		sc.close();
		return new Point(x, y);
	}

	public void newSegment(Point p1, Point p2) throws IOException, ClassNotFoundException{
		inStream = new ObjectInputStream(sock.getInputStream());
		outStream = new ObjectOutputStream(sock.getOutputStream());

		outStream.writeObject(ServerInterface.NEW_SEGMENT);
		outStream.writeObject(p1);
		outStream.writeObject(p2);

		String response = inStream.readObject().toString();
		if(response.equals(ServerInterface.SUCCESS)){
			System.out.println("SUCCESS");
		}
		else
			System.out.println("FAILURE, points not valid");

		outStream.flush();
		inStream.close();
		outStream.close();
	}

	public Point symmetrical(Point p) throws IOException, ClassNotFoundException{
		inStream = new ObjectInputStream(sock.getInputStream());
		outStream = new ObjectOutputStream(sock.getOutputStream());

		outStream.writeObject(ServerInterface.SYMMETRICAL);
		outStream.writeObject(p);
		Object response = inStream.readObject();
		Point sym = null;
		if(response instanceof Point){
			sym = (Point) response;
			System.out.println("x: "+sym.getX()+" y: "+sym.getY());
		}else{
			String r = response.toString();
			System.out.println("Server says: "+r);
		}

		outStream.flush();
		inStream.close();
		outStream.close();
		return sym;
	}

	public void list() throws IOException, ClassNotFoundException{
		inStream = new ObjectInputStream(sock.getInputStream());
		outStream = new ObjectOutputStream(sock.getOutputStream());

		outStream.writeObject(ServerInterface.GET_LIST);
		Object o = null;
		while(!(o = inStream.readObject()).equals("DONE")){
			Point p = (Point) o;
			System.out.println("x: "+p.getX()+" y: "+p.getY());
		}
		System.out.println("----Done----");

		outStream.flush();
		inStream.close();
		outStream.close();	
	}

	public void end() throws IOException{
		outStream = new ObjectOutputStream(sock.getOutputStream());
		outStream.writeObject(ServerInterface.END_STRING);
		outStream.flush();
		outStream.close();
	}

}