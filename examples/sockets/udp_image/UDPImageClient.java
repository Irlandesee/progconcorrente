import java.net.*;
import java.io.*;

public class UDPImageClient{

	public static void main(String[] args) throws Exception{
		DatagramSocket cs = new DatagramSocket();
		InetAddress ip_address = InetAddress.getByName("localhost");
		byte[] sendData = "START".getBytes();
		DatagramPacket sendPacket = new DatagramPacket(
			sendData, sendData.length, ip_address, 9865);
		cs.send(sendPacket);
		File f = new File("./image.jpg");
		FileOutputStream fos = new FileOutputStream(f);
		while(true){
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			cs.receive(receivePacket);
			String modSentence = new String(receivePacket.getData());
			if(modSentence.startsWith("END_FILE")){
				System.out.println("RECEIVED FROM SERVER: " +modSentence);
				fos.close();
				break;
			}
			fos.write(receivePacket.getData(), 0, receivePacket.getLength());

		}
		cs.close();
		System.out.println("CLIENT: finished");

	}

}