package es1;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.LinkedList;

public class SegmentServer{
	

	public static final int PORT = 8080;
	private static final String END_STRING = "END";
	private static final String NEW_SEGMENT = "NEW_SEGMENT";
	private static final String SYMMETRICAL = "SYMMETRICAL";
	private static final String GET_LIST = "GET_LIST";

	private static Point findSimmetric(Point p){
		Segment s = new Segment();

		return s.simmetric(p);
	}

	public static void main(String[] args) throws IOException{
		ServerSocket ss = null;
		Socket sock = null;


		try{
			ss = new ServerSocket(PORT);
			sock = ss.accept();
			try{
				LinkedList<Point> pointsReceived = new LinkedList<Point>();
				LinkedList<Segment> segments = new LinkedList<Segment>();
				System.out.println("SS: new connection accepted");
				BufferedReader inStream = new BufferedReader(
					new InputStreamReader(sock.getInputStream()));
				PrintWriter outStream = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(
						sock.getOutputStream())), true);
				String str = "";
				while(!(str = inStream.readLine()).equals(END_STRING)){
					if(str.equals(NEW_SEGMENT)){
						String coordinates = inStream.readLine();
						String[] tmp = coordinates.split(" ");
						/** coordinates format
						 * 1,2 2,3
						 * **/
						String[] p1Coordinates = tmp[0].split(",");
						String[] p2Coordinates = tmp[1].split(",");
						Point p1 = new Point(Double.parseDouble(p1Coordinates[0]),
								Double.parseDouble(p1Coordinates[1]));
						Point p2 = new Point(Double.parseDouble(p2Coordinates[0]),
								Double.parseDouble(p2Coordinates[1]));
						Segment s = new Segment();
						if(s.set(p1, p2)){
							segments.add(s);
							pointsReceived.add(p1);
							pointsReceived.add(p2);
							System.out.println("es1.Segment was added to list");
							System.out.printf("es1.Point List size: %d\n", pointsReceived.size());
							System.out.printf("es1.Segment list size: %d\n", segments.size());
							outStream.println("OK");
						}else{
							System.out.println("es1.Segment non valid");
							outStream.println("KO");
						}
					}
					else if(str.equals(SYMMETRICAL)){
						System.out.println("Finding symmetrical");
						String coordinates = inStream.readLine();
						String[] tmp = coordinates.split(",");
						Point p1 = new Point(Double.parseDouble(tmp[0]),
								Double.parseDouble(tmp[1]));
						if(pointsReceived.contains(p1)){
							Point simm = findSimmetric(p1);
							System.out.printf("Symmetrical: %d,%d\n", simm.getX(), simm.getY());
							outStream.println(simm.getX()+","+simm.getY());
						}
						else
							outStream.println("KO");
					}
					else if(str.equals(GET_LIST)){
						System.out.println("writing list to stream");
						for(Point p : pointsReceived){
							outStream.println(p.toString());
						}
						outStream.println("DONE");
						System.out.println("Done");
					}
					else{
						System.out.println("Command not recognized");
					}
				}
				System.out.println("connection terminated");
				inStream.close();
				outStream.close();
			}catch(IOException e1){
				e1.printStackTrace();
			}
		}catch(IOException e){
			sock.close();
			e.printStackTrace();
		}finally{
			System.out.println("Closing ss @"+PORT);
			ss.close();
		}

	} 

}