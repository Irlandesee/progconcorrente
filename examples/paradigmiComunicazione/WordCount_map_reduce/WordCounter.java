import java.util.concurrent.*;
import java.io.*;

public class WordCounter implements Runnable {
	CyclicBarrier cyclicBarrier;
	int wordCount=0;
	int startFile, endFile;
	WordCounter(CyclicBarrier c, int startFile, int endFile) {
		cyclicBarrier=c;
		this.startFile=startFile;
		this.endFile=endFile;
		new Thread(this).start();
	}
	public void run() {
		BufferedReader br = null;
		try {
			for(int i=startFile; i<endFile; i++) {
				br=null;
				String fileName="file_" + i + ".txt";
				System.out.println("leggo "+fileName);
				br=new BufferedReader(new FileReader("/home/gigi/Documents/Didattica/Prog_CD/Borse/"+fileName));
				String line=br.readLine();
				while(line!=null) {
					String[] wordArray=line.split("\\ s+");
					wordCount+=wordArray.length;
					line=br.readLine();
				}
			}
			System.out.println("GOING TO WAIT");
			cyclicBarrier.await();
		} catch(Exception exc) {
			System.out.println(exc);
		}
		try { br.close(); } catch (IOException e) {}
	}
}


