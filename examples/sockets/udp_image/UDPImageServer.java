import java.net.*;
import java.io.*;

public class UDPImageServer{

	public static void main(String[] args) throws Exception{

		DatagramSocket ss = new DatagramSocket(9865);
		while(true){
			//receive
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			System.out.println("waiting");
			ss.receive(receivePacket);
			String sentence = new String(receivePacket.getData());;
			System.out.println("RECEIVED: " +sentence);
			//send
			InetAddress ip_address = receivePacket.getAddress();
			int port = receivePacket.getPort();
			File f = new File("image.jpg");
			System.out.println("Starting to send file: "+f);
			FileInputStream fis = new FileInputStream(f);
			int size = 0;


			while(true){
				byte[] sendData = new byte[1024];
				size = fis.read(sendData);
				if(size == -1){
					byte[] sendEOFData = "END_FILE".getBytes();
					System.out.println("Send: " +new String(sendEOFData));
					DatagramPacket sendPacket = new DatagramPacket(
						sendEOFData, sendEOFData.length, ip_address, port);
					ss.send(sendPacket);
					break;
				}
				else{
					DatagramPacket sendPacket = new DatagramPacket(sendData, size, ip_address, port);
					ss.send(sendPacket);
				}
				Thread.sleep(1);
			}


		}

	}

}