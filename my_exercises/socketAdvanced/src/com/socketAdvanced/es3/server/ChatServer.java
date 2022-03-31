package com.socketAdvanced.es3.server;

import java.net.*;
import java.io.*;

public class ChatServer {

    private static final int port = 9999;

    public static void main(String[] args){
        ServerSocket ss;
        try{
            ss = new ServerSocket(port);
            while(true){

            }
        }catch(IOException io){io.printStackTrace();}
    }

}
