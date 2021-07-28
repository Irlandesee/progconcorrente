import java.io.*;

public class TheRestorer {
	String fileName;
	PersistentClock pc;
	TheRestorer(String s){
		fileName=s;
	}
	void restorePersistentClock() {
		ObjectInput is=null;
		try {
			is = new ObjectInputStream(new FileInputStream(fileName));
		} catch (IOException e) {	}
		try {
			pc = (PersistentClock) is.readObject();
		} catch (ClassNotFoundException e) {
			System.err.println("Class not found");
			return;
		} catch (IOException e) {
			System.err.println("I/O error reading serialized object");
			return;
		}
		System.out.println("Persistent clock restored");
		try {
			is.close();
		} catch (IOException e) {	}
	}
	public static void main(String[] args) throws IOException{
		TheRestorer tr=null;
		if(args.length==1) {
			tr=new TheRestorer(args[0]);
		} else {
			tr=new TheRestorer("../../tmp.ser");			
		}
		tr.restorePersistentClock();
	}
}
