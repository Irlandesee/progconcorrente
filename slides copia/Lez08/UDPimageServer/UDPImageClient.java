import java.io.*;
import java.net.*;

public class UDPImageClient {
	public static void main(String args[]) throws Exception {
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData;
		sendData = "START".getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket);
		File file = new File("./race-condition-copy.png");
		FileOutputStream fos = new FileOutputStream(file);
		while (true) {
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);			
			String modifiedSentence = new String(receivePacket.getData());
			if (modifiedSentence.startsWith("END_FILE")) {
				System.out.println("RECEIVED FROM SERVER: " + modifiedSentence);
				fos.close();
				break;
			}
			fos.write(receivePacket.getData(), 0, receivePacket.getLength());
		}
		clientSocket.close();
		System.out.println("CLIENT: finished");
	}
}
