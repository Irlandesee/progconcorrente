import java.io.*;
import java.net.*;

public class ClienteAstaThread extends Thread{
	private int maxTries = 3+(int)(Math.random()*4);
	private int numTries=0;
	private Offerta currentOffer;
	private final double myIncrease=1.06;
	private String myName;
	private Socket mySocket;
	private ObjectInputStream obj_in_s;
	private ObjectOutputStream obj_out_s;

	public ClienteAstaThread(InetAddress addr, int port, String n) throws IOException{	
		this.mySocket = new Socket(addr, port);
		this.myName=n;
		this.currentOffer=new Offerta(0, myName);
		this.obj_out_s = new ObjectOutputStream(mySocket.getOutputStream());
		this.obj_in_s = new ObjectInputStream(mySocket.getInputStream());
		start();
	}
	public void run(){
		boolean finito=false;
		Offerta newOff=null;
		String str;
		while(!finito && numTries<maxTries){			
			try {
				obj_out_s.writeObject("read");
				Offerta off= (Offerta) obj_in_s.readObject();
				System.out.println(myName+" read offer by "+off.getWho()+" worth "+off.getAmount());
				currentOffer=off;
				if(!off.getWho().equals(myName)){	// rilancia
					obj_out_s.writeObject("offer");
					newOff=new Offerta((int) (currentOffer.getAmount()*myIncrease), myName);
					obj_out_s.writeObject(newOff);
					str = (String) obj_in_s.readObject();
					if(str.equals("KO")){
						Thread.sleep(100);						
					}
				} else {
					Thread.sleep(2000);
				}
			} catch (IOException | InterruptedException | ClassNotFoundException e1) { break; }
			numTries++;
		}  // fine ciclo
		try {
			obj_out_s.writeObject("END");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(myName+" exits");
	}
}
