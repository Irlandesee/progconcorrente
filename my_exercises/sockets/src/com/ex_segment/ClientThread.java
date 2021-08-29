package com.ex_segment;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;

import com.ex_segment.point.Point;
import com.ex_segment.segment.Segment;

public class ClientThread extends Thread{

    private Socket s;
    private BufferedReader in;
    private PrintWriter out;
    private static int threadCount = 0;
    private int id;

    public static int threadCount(){
        return threadCount;
    }

    public ClientThread(InetAddress addr, int _id){
        System.out.println("Making client: " + _id);
        id = _id;
        threadCount++;
        try{
            s = new Socket(addr, 9999);
        }catch(IOException e){
            System.err.println("Socket has failed");
        }
        try{
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);

        }catch(IOException e){
            try{
                threadCount--;
                s.close();
            }catch(IOException e2){System.err.println("Socket not closed");}
        }
    }

    private void sleep(int t, int t1){
        try{
            Thread.sleep(ThreadLocalRandom.current().nextInt(t, t1));
        }catch(InterruptedException e){ }
    }

    public void run(){
        try{
            for(int i = 0; i < 5; i++){
                sleep(100, 200);
                boolean segOK = false;
                while(!segOK){
                    System.out.println("Client_"+id+"asking for segment creation");
                    out.println("NewSegment");
                    Point p1 = new Point(100*Math.random(), 100*Math.random());
                    System.out.println("Client_"+id+" point 1: (" + p1.getX() + " " + p1.getY() + ")");
                    out.println(p1.getX());
                    out.println(p1.getY());
                    Point p2 = new Point(100*Math.random(), 100*Math.random());
                    System.out.println("Client_"+id+" point 1: (" + p1.getX() + " " + p1.getY());
                    out.println(p2.getX());
                    out.println(p2.getY());
                    String str = in.readLine();
                    if(str.equals("OK"))
                        segOK = true;
                    sleep(2, 8);
                    System.out.println("Client_"+id+": segment creation response received: ");
                }
                sleep(10, 20);
                out.println("Simmetric");
                Point px = new Point(100*Math.random(), 100*Math.random());
                System.out.println("Client_"+id+" given point:{"+px.getX()+" "+px.getY()+"}");
                out.println(px.getX());
                out.println(px.getY());
                String s1 = in.readLine();
                String s2 = in.readLine();
                Point ps = new Point(Double.parseDouble(s1), Double.parseDouble(s2));
                System.out.println("Client_"+id+"received point: {"+ps.getX()+" "+ps.getY()+"}");
            }
            out.println("END");
        }catch(IOException e){
            System.out.println("IO EXception");
        }finally{
            try{
                s.close();
            }catch(IOException e){
                System.err.println("Socket not closed");
            }
            threadCount--;
        }

    }




}
