import java.io.*;
import java.util.*;

public class TheMain {
	String fileName;
	TheMain(String fn){
		fileName=fn;
	}
	void point_to_file() {
		Point p=new Point(2,3);
		ObjectOutputStream output=null;
		try{
			output=new ObjectOutputStream(new FileOutputStream(fileName));
			output.writeObject(p);
			output.close();
		}
		catch (FileNotFoundException e) { }
		catch(IOException ioe){ }
		System.out.println("Serializzazione completata.");
	}
	void point_from_file() {
		ObjectInputStream ois = null;
		Point p = null;
		try{
			ois = new ObjectInputStream(new FileInputStream(fileName));
			p = (Point) ois.readObject();
			ois.close();
			System.out.println(p);
		}
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		if(args.length==1) {
			TheMain tm = new TheMain(args[0]);
			tm.point_to_file();
			tm.point_from_file();
		} else {
			System.out.println("filename missing");
		}
	}
}


