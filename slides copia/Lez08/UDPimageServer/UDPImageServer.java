import java.io.*;
import java.net.*;
public class UDPImageServer {

	void exec(String fileName) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(9876);
		while (true) {
			System.out.println("server cycle starts ");
			// RECEIVE
			byte[] receiveData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			System.out.println("waiting ");
			serverSocket.receive(receivePacket);
			String sentence = new String(receivePacket.getData());
			System.out.println("RECEIVED: " + sentence);
			// SEND
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			File file = new File(fileName);
			System.out.println("Starting to send file: " + file);
			FileInputStream fis = new FileInputStream(file);
			int size = 0;
			while (true) {
				byte[] sendData = new byte[1024];
				size = fis.read(sendData);
				if(size == -1) {
					byte[] sendEOFData = "END_FILE".getBytes();
					System.out.println("Send: " + new String(sendEOFData));
					DatagramPacket sendPacket = new DatagramPacket(sendEOFData, sendEOFData.length, IPAddress, port);
					serverSocket.send(sendPacket);
					break;
				}
				DatagramPacket sendPacket = new DatagramPacket(sendData, size, IPAddress, port);
				serverSocket.send(sendPacket);
				Thread.sleep(1);
			}
		}

	}
	public static void main(String args[]) throws Exception {
		new UDPImageServer().exec(args[0]);
	}
}
