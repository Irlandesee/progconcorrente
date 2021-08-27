package com.ex_segment;

import com.ex_segment.point.Point;
import com.ex_segment.segment.Segment;

import java.net.*;
import java.io.*;

public class SegmentClient {

    public static void main(String[] args){
        /**
        Point p1 = new Point(0.0, 0.0);
        Point p2 = new Point(4.0, 4.0);
        Point px = new Point(0.0, 4.0);
        System.out.println("p1: " +p1.toString()
        +"\np2: "+p2.toString());

        Segment sgm = new Segment();
        sgm.set(p1, p2);
        System.out.println(sgm.toString());

        Point simm = sgm.simmetric(px);
        System.out.println("px: " + px.toString());
        System.out.println("simm: " +simm.toString());
         **/
        Socket s;
        BufferedReader in;
        PrintWriter out;

        try{
			s = new Socket("localhost", 9999);
			in = new BufferedReader(
				new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(s.getOutputStream())), true);

			out.println("NEWSEGMENT");
			try{
				String response = "";
				int c;
				StringBuffer dataToSend = new StringBuffer(128);
				while((response = in.readLine()) != null){
					System.out.println("From ServerSlave: "+response);
					while((c = System.in.read()) != '\n'){
						dataToSend.append((char) c);
					}
					System.out.println("From client: "+dataToSend);
					out.println(dataToSend.toString());
					dataToSend.setLength(0);
				}
				out.close();
				in.close();
				s.close();
			}catch(IOException e){
				e.printStackTrace();
			}

		}catch(IOException e){
			System.err.println("Couldn't create stream socket");
			System.exit(1);
		}


    }

}
