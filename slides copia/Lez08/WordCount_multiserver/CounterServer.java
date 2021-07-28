import java.io.*;
import java.net.*;

public class CounterServer {
	int wordCount=0;
	String filePath;
	int startFile, endFile;
	int myPORT;
	BufferedReader in;
	PrintWriter out; 
	CounterServer(String path, String port){
		myPORT=Integer.parseInt(port);
		filePath=path;
	}
	void serve() throws IOException {
		String str;
		ServerSocket server = new ServerSocket(myPORT);
		Socket connection = null;
		while (true) {
			try {
				connection = server.accept();
				System.out.println("Connection accepted: "+connection);
				in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
				out=new PrintWriter(new BufferedWriter(new OutputStreamWriter(connection.getOutputStream())),true);
				str=in.readLine();
				System.out.println("server received:"+str);
				this.startFile=Integer.parseInt(str);
				str=in.readLine();
				System.out.println("server received:"+str);
				this.endFile=Integer.parseInt(str);
				count();  // qui c'e` il servizio
				out.println(wordCount);
				System.out.println("server sent:"+wordCount);
				connection.close();
			}
			catch (IOException ex) {System.err.println("server IO error");}
			finally {
				try {
					if (connection != null) connection.close();
				} catch (IOException ex) {}
			}
		}
	}
	
	
	public void count() {
		BufferedReader br = null;
		this.startFile=startFile;
		this.endFile=endFile;
		try {
			for(int i=startFile; i<endFile; i++) {
				br=null;
				String fileName=filePath+"/file_" + i + ".txt";
//				System.out.println("leggo "+fileName);
				br=new BufferedReader(new FileReader(fileName));
				String line=br.readLine();
				while(line!=null) {
					String[] wordArray=line.split("\\ s+");
					wordCount+=wordArray.length;
					line=br.readLine();
				}
			}
			System.out.println("finito");
			br.close();
		} catch(Exception exc) {
			System.out.println(exc);
		}
	}
	public static void main(String args[]) throws IOException {
		if(args.length!=2) {
			System.out.println("usage: java CounterServer <file path> <PORT>");
		} else {
			CounterServer CS=new CounterServer(args[0], args[1]);
			CS.serve();
		}
	}
}
