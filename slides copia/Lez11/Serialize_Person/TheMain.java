import java.io.*;
import java.util.*;

public class TheMain {
	String fileName;
	TheMain(String fn){
		fileName=fn;
	}
	void employee_to_file() {
		Employee empl=new Employee("Rossi", 100, 3000);
		empl.display();
		ObjectOutputStream output=null;
		try{
			output=new ObjectOutputStream(new FileOutputStream(fileName));
			output.writeObject(empl);
			output.close();
		}
		catch (FileNotFoundException e) { }
		catch(IOException ioe){ }
		System.out.println("Serializzazione completata.");
	}
	void employee_from_file() {
		ObjectInputStream ois = null;
		Employee emp = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(fileName));
		} catch (IOException e) {
			System.err.println("stream creation failed");
			System.exit(0);
		}
		try {
			emp = (Employee) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			System.err.println("Employee deserialization failed");
			System.exit(0);
		}
		try {
			ois.close();
		} catch (IOException e) {
			System.err.println("Stream closing failed");
			System.exit(0);
		}
		emp.display();
	}
	public static void main(String[] args) {
		if(args.length==1) {
			TheMain tm = new TheMain(args[0]);
			tm.employee_to_file();
			tm.employee_from_file();
		} else {
			System.out.println("filename missing");
		}
	}
}


