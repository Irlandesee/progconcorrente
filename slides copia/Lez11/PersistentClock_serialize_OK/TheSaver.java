import java.io.*;

public class TheSaver {
	String fileName;
	PersistentClock pc;
	TheSaver(String s){
		pc = new PersistentClock(1000);
		fileName=s;
	}
	void savePersistentClock() {
		FileOutputStream fos=null;
		ObjectOutput os=null;
		try {
			fos = new FileOutputStream(fileName);
		} catch (IOException e) {
			System.err.println("KO");
			System.exit(0);
		}
		try {
			os = new ObjectOutputStream(fos);
		} catch (IOException e) {
			System.err.println("Serialization to file failed!");
		}
		try {
			os.writeObject(pc);
			os.flush();  
			os.close();
		} catch (IOException e) { }
	}
	public static void main(String[] args) throws IOException, InterruptedException{
		TheSaver ts=null;
		if(args.length==1) {
			ts=new TheSaver(args[0]);
		} else {
			ts=new TheSaver("../../tmp.ser");			
		}
		ts.savePersistentClock();
		Thread.sleep(10000);
		System.out.println("Shutdown");
		System.exit(0);
	}
}
