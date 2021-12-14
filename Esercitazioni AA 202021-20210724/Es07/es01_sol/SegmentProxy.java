import java.io.*;
import java.net.*;

public class SegmentProxy implements SegmentInterface{
	Socket socket;
	ObjectInputStream in;
	ObjectOutputStream out;
	SegmentProxy(InetAddress addr, int portNum){
		try {
			socket = new Socket(addr, portNum);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			System.err.println("Socket failed");
		}
		System.out.println("Proxy ready");
	}
	public boolean set(Point p1, Point p2) {
		String str;
		try {
			out.writeObject("NewSegment");
			out.writeObject(p1);
			out.writeObject(p2);
			str = (String) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			return false;
		}
		return (str.equals("OK"));
	}
	public Point simmetric(Point px) {
		Point pr=null;
		try {
			out.writeObject("Simmetric");
			out.writeObject(px);
			pr = (Point) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			return null;
		}
		return pr;
	}
	public void quit() {
		try {
			out.writeObject("END");
		} catch (IOException e) {}
	}
}
