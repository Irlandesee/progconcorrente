import java.io.*;
import java.util.*;

public class TheMain {
	String fileName;
	TheMain(String fn){
		fileName=fn;
	}
	void int_to_file() {
		ObjectOutput os;
		int i=11;
		try {
			os = new ObjectOutputStream(new FileOutputStream(fileName));
			os.writeInt(i);
			os.flush();
			os.close();
		} catch (IOException e) {
			System.err.println("I/O error");
			System.exit(0);
		}		
	}
	void int_from_file() {
		ObjectInput is;
		try {
			is = new ObjectInputStream(new FileInputStream(fileName));
			int j = is.readInt();
			System.out.println("read: "+j);
			is.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
			System.exit(0);
		} catch (IOException e) {
			System.err.println("I/O error");
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		if(args.length==1) {
			TheMain tm = new TheMain(args[0]);
			tm.int_to_file();
			tm.int_from_file();
		} else {
			System.out.println("filename missing");
		}
	}
}


