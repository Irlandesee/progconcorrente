import java.io.*;
import java.net.*;

public class ClienteAstaThread extends Thread{
	private int maxTries = 3+(int)(Math.random()*4);
	private int numTries=0;
	private Offerta currentOffer;
	private final double myIncrease=1.06;
	private String myName;
	private Proxy lAsta;

	public ClienteAstaThread(InetAddress addr, int port, String n) throws IOException{
		lAsta=new Proxy(addr, port);
		this.myName=n;
		this.currentOffer=new Offerta(0, myName);
		start();
	}
	public void run(){
		boolean finito=false;
		Offerta newOff=null;
		String str;
		while(!finito && numTries<maxTries){			
			try {
				currentOffer=lAsta.readCurrentOffer();
				System.out.println(myName+" read offer by "+currentOffer.getWho()+
						" worth "+currentOffer.getAmount());
				if(!currentOffer.getWho().equals(myName)){	// rilancia
					if(!lAsta.makeOffer((int) (currentOffer.getAmount()*myIncrease), myName)) {
						Thread.sleep(100);						
					}
				} else {
					Thread.sleep(2000);
				}
			} catch (IOException | InterruptedException | ClassNotFoundException e1) { break; }
			numTries++;
		}  // fine ciclo
		try {
			lAsta.quit();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(myName+" exits");
	}
}
