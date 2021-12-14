import java.io.*;
import java.net.Socket;

public class ServerAstaThread extends Thread {
	private Asta lAsta;
	private Socket cliSocket;
	private ObjectInputStream obj_in_s;
	private ObjectOutputStream obj_out_s;

	public ServerAstaThread(Asta a, Socket s) throws IOException{
		this.lAsta=a;
		this.cliSocket=s;
		this.obj_out_s = new ObjectOutputStream(s.getOutputStream());
		this.obj_in_s = new ObjectInputStream(s.getInputStream());
		start();
	}
	public void run(){
		Offerta off;
		while(true) {
			String str;
			try {
				str = (String) obj_in_s.readObject();
			} catch (IOException | ClassNotFoundException e) {
				str="END";
			}
			System.out.println("Server received "+str);
			if(str.equals("END")) break;
			if(str.equals("read")){
				off=lAsta.leggi_offerta();
				try {
					obj_out_s.writeObject(off);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(str.equals("offer")){
				try {
					off = (Offerta) obj_in_s.readObject();
					System.out.println("server ha ricevuto offerta da "+off.getWho()+" per "+off.getAmount());
					if(lAsta.nuova_offerta(off)){
						obj_out_s.writeObject("OK");
					} else {
						obj_out_s.writeObject("KO");
					}
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			} 
		}
		try {
			cliSocket.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
}
