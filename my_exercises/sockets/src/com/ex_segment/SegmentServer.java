package com.ex_segment;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class SegmentServer {

    private static final int SERVER_PORT = 9999;

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(SERVER_PORT);
        System.out.println("Server Started @:"+SERVER_PORT);
        try{
            while(true){
                Socket s = ss.accept();
                System.out.println("Server accepts new client");
            }
        }finally{
            ss.close();
        }
    }

}
