package com.chat.client;

import java.net.InetAddress;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Client{

	private static int serverID = 1;

	private static final int NUM_THREADS = 10;
	private static final int MAX_THREAD = 4;

	private static final int serverPort = 9999;

	public static void main(String[] args) 
		throws IOException, InterruptedException{
			InetAddress addr = InetAddress.getByName(null);
			System.out.println(addr);
			Socket s = new Socket(addr, serverPort);
			new ClientSlaveMaster(s).exec();

	}

	public static int getServerID(){return serverID;}


}