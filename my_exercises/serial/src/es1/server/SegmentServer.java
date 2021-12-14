package es1.server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.io.IOException;

public class SegmentServer{

	public static final int PORT = 8080;

	public static void main(String[] args){
		ServerSocket ss = null;
		Socket sock = null;
		LinkedList<Thread> serverSlaves = new LinkedList<Thread>();
		int i = 0;
		try{
			while((sock = ss.accept()) != null){
				Thread t = new ServerSlave(i, sock);
				serverSlaves.add(t);
				i++;
			}	
		}catch(IOException io){io.printStackTrace();}
		finally{
			try{
				ss.close();	
			}catch(IOException io2){io2.printStackTrace();}
		}
	}

}