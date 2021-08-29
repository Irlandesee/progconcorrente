package com.ex_segment;

import com.ex_segment.point.Point;
import com.ex_segment.segment.Segment;

import java.net.*;
import java.io.*;

public class SegmentClient {

	private static int NUM_THREADS = 10;
    private static int MAX_THREADS = 4;

    public static void main(String[] args) throws IOException, InterruptedException{
        InetAddress addr = InetAddress.getByName(null);
        System.out.println(addr.toString());
        int i = 0;
        while(i < NUM_THREADS){
            if(ClientThread.threadCount() < MAX_THREADS){
                new ClientThread(addr, i++);
            }
            else
                Thread.sleep(10);
            Thread.sleep(100);
        }


    }

}
