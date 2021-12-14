import java.io.*;
import java.net.*;

public class Proxy {
	Socket mySocket;
	ObjectInputStream obj_in_s;
	ObjectOutputStream obj_out_s;
	Proxy(InetAddress addr, int port) throws IOException{
		this.mySocket = new Socket(addr, port);
		this.obj_out_s = new ObjectOutputStream(mySocket.getOutputStream());
		this.obj_in_s = new ObjectInputStream(mySocket.getInputStream());
	}
	public Offerta readCurrentOffer() throws IOException, ClassNotFoundException {
		obj_out_s.writeObject("read");
		Offerta off= (Offerta) obj_in_s.readObject();
		return off;
	}
	public boolean makeOffer(int amount, String name) throws IOException, ClassNotFoundException {
		obj_out_s.writeObject("offer");
		Offerta newOff=new Offerta(amount, name);
		obj_out_s.writeObject(newOff);
		return (boolean) obj_in_s.readObject();
	}
	public void quit() throws IOException {
		obj_out_s.writeObject("END");
	}
}
