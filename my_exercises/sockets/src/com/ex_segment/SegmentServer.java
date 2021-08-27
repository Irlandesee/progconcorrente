package com.ex_segment;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class SegmentServer {

    private static final int SERVER_PORT = 9999;

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(SERVER_PORT);
        Socket s = null;
        try{

            while((s = ss.accept()) != null)
                new ServerSlave(s);

        }catch(IOException e){
            s.close();
            System.out.println("IOException: ");
            e.printStackTrace();
        }
        finally{
            System.out.printf("Chiudo Server socket @: %d\n", SERVER_PORT);
            ss.close();
        }
    }

}
