package es1.server;

import es1.point.Point;
import es1.segment.Segment;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.LinkedList;

public class ServerSlave extends Thread{

	private final String END_STRING = "END";
	private final String NEW_SEGMENT = "NEW_SEGMENT";
	private final String SYMMETRICAL = "SYMMETRICAL";
	private final String GET_LIST = "GET_LIST";
	private final String SUCCESS = "OK";
	private final String FAILURE = "KO";

	private Socket sock;
	private int slaveID;

	private ObjectInputStream inStream;
	private ObjectOutputStream outStream;

	private LinkedList<Segment> segments;
	private LinkedList<Point> points;

	public ServerSlave(int slaveID, Socket sock){
		this.slaveID = slaveID;
		this.sock = sock;
		points = new LinkedList<Point>();
		segments = new LinkedList<Segment>();
	}

	public void run(){
		try{
			inStream = new ObjectInputStream(sock.getInputStream());
			String command = "";
			while(!(command = inStream.readObject().toString()).equals(END_STRING)){
				switch(command){
					case NEW_SEGMENT:
						//receive objects
						Point p1 = null, p2 = null;
						try{
							p1 = (Point) inStream.readObject();
							p2 = (Point) inStream.readObject();
						}catch(ClassNotFoundException ce){ce.printStackTrace();}

						Segment seg = new Segment();
						if(seg.set(p1, p2)){
							System.out.println("Creating and saving new segment");
							segments.add(seg);
							writeSuccess(SUCCESS);
						}
						else{
							System.out.println("Segment not valid");
							writeSuccess(FAILURE);
						}
						break;
					case SYMMETRICAL:
						System.out.println("Finding symmetrical");
						Point p = null;
						try{
							p = (Point) inStream.readObject();
						}catch(ClassNotFoundException ce){ce.printStackTrace();}

						if(points.contains(p)){
							Segment s = new Segment();
							Point sym = s.simmetric(p);
							writeSymmetrical(sym);
						}
						else
							writeSuccess(FAILURE);
						break;
					case GET_LIST:
						System.out.println("Sending segment list");
						writeList();
						break;
					default:
						System.out.println("comando non riconosciuto");
						break;
				}
				//quit
				System.out.println("connection terminated");
				inStream.close();
			}
		}catch(IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
	}

	/**
	 * Sends back a serialized Segment Object
	 * */
	private void writeSegment(Segment s) throws IOException{
		outStream = new ObjectOutputStream(sock.getOutputStream());
		outStream.writeObject(s);
		outStream.flush();
		outStream.close();
	}

	private void writeSymmetrical(Point sym) throws IOException{
		outStream = new ObjectOutputStream(sock.getOutputStream());
		outStream.writeObject(sym);
		outStream.flush();
		outStream.close();
	}

	private void writeList() throws IOException{
		outStream = new ObjectOutputStream(sock.getOutputStream());

		for(Segment s : segments)
			outStream.writeObject(s);

		outStream.writeObject("DONE");
		outStream.flush();
		outStream.close();
	}

	private void writeSuccess(String success) throws IOException{
		outStream = new ObjectOutputStream(sock.getOutputStream());
		outStream.writeObject(success);
		outStream.flush();
		outStream.close();
	}

}