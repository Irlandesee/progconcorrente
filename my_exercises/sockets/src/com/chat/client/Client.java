package com.chat.client;

import java.net.*;
import java.io.*;

public class Client{


	private static final int NUM_THREADS = 10;
	private static final int MAX_THREAD = 4;

	public static void main(String[] args) 
		throws IOException, InterruptedException{
			InetAddress addr = InetAddress.getByName(null);
			System.out.println(addr);
			int i = 0;
			
			//new ClientSlave(addr, 1);

	}


}