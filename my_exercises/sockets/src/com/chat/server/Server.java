package com.chat.server;

import java.net.*;
import java.io.*;

public class Server{

	private static final int SERVER_PORT = 8080;
	private static int clientID = 0;

	public static void main(String[] args) throws IOException{

		ServerSocket ss = new ServerSocket(SERVER_PORT);
		System.out.println("Server started @ port:"+SERVER_PORT);
		try{
			while(true){
				Socket s = ss.accept();
				System.out.println("server accepts a new client");
				clientID++;
				new ServerSlaveMaster(s);
			}
		}finally{
			ss.close();
		}
	}

	public static int getClientID() {
		return clientID;
	}
}