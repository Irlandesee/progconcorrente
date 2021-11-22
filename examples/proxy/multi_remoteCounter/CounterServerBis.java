import java.io.*;
import java.net.*;

public class CounterServerBis implements ServerInterface, Runnable{

	private Socket _s;
	private int _counter = 0;
	private BufferedReader iStream;
	private PrintWriter oStream;

	public CounterServerBis(Socket s){
		_s = s;
		try{
			iStream = new BufferedReader(new InputStreamReader(
				s.getInputStream()));
			oStream = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(s.getOutputStream())), true);
		}catch(IOException e){ }
	}

	//implementazione metodi relativi all'interfaccia
	public int sum(int s) throws IOException{
		_counter += s;
		return _counter;
	}

	public int reset() throws IOException{
		_counter = 0;
		return _counter;
	}

	public int increment() throws IOException{
		_counter++;
		return _counter;
	}

	public void close(){
		System.out.println("Closing...");
		try{
			_s.close();
		}catch(IOException e){ }
	}

	public void run(){
		try{
			while(!_s.isClosed()){
				int result = 0;
				String myOper = iStream.readLine();
				switch(myOper){
					case "<incr>":
						result = increment();
						break;
					case "<reset>":
						result = reset();
						break;
					case "<sum>":
						String[] temp = myOper.split(" ");
						String op = temp[0];
						String add = temp[1];
						result = sum(Integer.parseInt(add));
						break;
					case "<end>":
						close();
						break;
					default:
						System.out.println("Operazione non riconosciuta: "+myOper);
						break;
				}
			}
		}catch(Exception e){
			close();
			e.printStackTrace();
		}
	}

}