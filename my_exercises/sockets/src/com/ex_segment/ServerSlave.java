package com.ex_segment;

import com.ex_segment.segment.Segment;
import com.ex_segment.point.Point;

import java.net.*;
import java.io.*;

public class ServerSlave extends Thread{

    private Socket s;
    private BufferedReader in;
    private PrintWriter out;
    private Segment seg;

    public ServerSlave(Socket _s){
        s = _s;
        try{
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
        }catch(IOException e){
            try{
                s.close();
            }catch(IOException e1){
                e.printStackTrace();
            }
        }
        start();
    }

    public void run(){
        try{
            while(true){
                String str = in.readLine();
                System.out.println("Received: " +str);
                if(str.equals("END"))
                    break;
                if(str.equals("NewSegment")){
                    //Lettura coordinate di 2 point
                    String s = in.readLine();
                    String s1 = in.readLine();
                    Point p1 = new Point(Double.parseDouble(s), Double.parseDouble(s1));
                    s = in.readLine();
                    s1 = in.readLine();
                    Point p2 = new Point(Double.parseDouble(s), Double.parseDouble(s1));
                    seg = new Segment();
                    boolean res = seg.set(p1, p2);
                    if(res){
                        out.println("OK");
                        System.out.println("Server: segment created");
                    }else{
                        out.println("KO");
                        System.out.println("Server: segment creation failed");
                    }
                }
                if(str.equals("Simmetric")){
                    //lettura di un oggetto Point
                    System.out.println("Server: simmetric computation started");
                    String s = in.readLine();
                    String s1 = in.readLine();
                    Point p = new Point(Double.parseDouble(s), Double.parseDouble(s1));
                    Point ps = seg.simmetric(p);
                    System.out.println("Server: computed Simmetric, "+ps.getX()+" "+ps.getY());

                    out.println(Double.toString(ps.getX()));
                    out.println(Double.toString(ps.getY()));
                    System.out.println("Server: sent simmetric");

                }
                System.out.println("closing");
            }
        }catch(IOException e){
            System.out.println("closing");
        }finally{
            try{
                s.close();
            }catch(IOException e){
                System.err.println("Socket not closed");
            }
        }


    }

}
