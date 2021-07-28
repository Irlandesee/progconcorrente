import java.io.*;
import java.util.*;

public class TheMain {
	String fileName;
	TheMain(String fn){
		fileName=fn;
	}
	void string_and_date_file() {
		ObjectOutput os;
		ObjectInput is;
		try {
			os = new ObjectOutputStream(new FileOutputStream(fileName));
			os.writeObject("Oggi");
			os.writeObject(new Date());
			os.flush();
			os.close();
			is = new ObjectInputStream(new FileInputStream(fileName));
			String s = (String) is.readObject();
			Date d = (Date) is.readObject();
			System.out.println(s + " " + d);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	void string_array_file() {
		String[] p = new String[]{"rosso","giallo","blu"};
		ObjectOutput os;
		try {
			os = new ObjectOutputStream(new FileOutputStream(fileName));
			os.writeObject(p);
			os.flush();
			os.close();
			ObjectInput is = new ObjectInputStream(new FileInputStream(fileName));
			String[] p1 = (String[]) is.readObject();
			for (String s: p1)
				System.out.print(s + " "); // rosso, giallo, blu, 
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if(args.length==1) {
			TheMain tm = new TheMain(args[0]);
			//tm.string_and_date_file();
			tm.string_array_file();
		} else {
			System.out.println("filename missing");
		}
	}
}


