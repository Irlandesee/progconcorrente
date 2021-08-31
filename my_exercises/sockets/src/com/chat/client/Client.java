package com.chat.client;

import java.net.InetAddress;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Client{

	private static int serverID = 0;

	private static final int NUM_THREADS = 10;
	private static final int MAX_THREAD = 4;

	public static void main(String[] args) 
		throws IOException, InterruptedException{
			InetAddress addr = InetAddress.getByName(null);
			System.out.println(addr);

			/**try{

			}catch(IOException e){
				e.printStackTrace();
			}**/

	}

	public static int getServerID(){return serverID;}


}