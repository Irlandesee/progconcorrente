import java.io.*;
import java.net.*;

public class UDPServer{

	public static void main(String[] args) throws Exception{
		DatagramSocket serverSocket = new DatagramSocket(9876);
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];

		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

		while(true){
			serverSocket.receive(receivePacket);
			printPacket(receivePacket);
			String sentence = new String(receivePacket.getData());
			InetAddress ip_address = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String capSentence = sentence.toUpperCase();
			sendData = capSentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip_address, port);
			serverSocket.send(sendPacket);
		}


	}

	private static void printPacket(DatagramPacket pktReceived){
		
		System.out.printf("---PacketReceived---\nIP_ADDRESS:%s\nPORT:%d\n---ENDPACKET\n", pktReceived.getAddress().toString(), pktReceived.getPort());
	}



}