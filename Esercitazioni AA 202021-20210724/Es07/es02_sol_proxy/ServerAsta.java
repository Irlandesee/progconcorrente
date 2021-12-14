import java.io.*;
import java.net.*;

public class ServerAsta {
	public static final int PORT=9999;
	final static int MAXWAIT=6000;
	public static void main(String[] args) throws IOException {
		Asta lAsta = new Asta(new Offerta(10000, "nessuno"), 0.05);
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Server asta pronto");
		s.setSoTimeout(MAXWAIT);
		while(true) {
			Socket cliSocket=null;
			try{
				cliSocket=s.accept();				
			} catch (SocketTimeoutException e){
				break;
			}
			cliSocket.setSoTimeout(3000);
			new ServerAstaThread(lAsta, cliSocket);
		} // fine ciclo accettazioni
		System.out.println("Aggiudicato a " +lAsta.leggi_offerta().getWho()+
				" per "+lAsta.leggi_offerta().getAmount()+" soldi");
		s.close();
	}
}


