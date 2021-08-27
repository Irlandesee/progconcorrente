package com.ex_segment;

import com.ex_segment.segment.Segment;
import com.ex_segment.point.Point;

import java.net.*;
import java.io.*;

public class ServerSlave extends Thread{

    private Socket s;
    private BufferedReader in;
    private PrintWriter out;

    private enum Command{
        waitforcommand,
        endcommand,
        newsegment,
        simmetrico
    }

    private final String OK_STRING = "OK";
    private final String KO_STRING = "KO";
    private final String WAIT_STRING = "WAIT";
    private final String END_STRING = "END";
    private final String NEWSEG_STRING = "NEWSEGMENT";
    private final String SIMM_STRING = "SIMMETRICO";

    private Command cmd = Command.waitforcommand;

    public ServerSlave(Socket _s){
        s = _s;
        try{
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
            System.out.println("Slave has started: "+Thread.currentThread().getId());
            start();
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        /**finally{
            try{
                System.out.println("Closing the socket");
                s.close();
            }catch(IOException e){
                System.out.println("Could not close socket ");
                e.printStackTrace();
            }

        }**/
    }

    public void run(){
        String inputCommand = "";
        try{

            while((inputCommand = in.readLine()) != null){
                String outLine = processInput(inputCommand);
                if(cmd == Command.waitforcommand){
                    out.println("waiting for command");
                    continue;
                }
                else if(cmd == Command.endcommand){
                    out.println("terminating...");
                    break;
                }
                out.println(outLine);
            }
            in.close();
            out.close();
            s.close();

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private String processInput(String command){
        String outStr = "";
        switch(command){
            case WAIT_STRING:
                cmd = Command.waitforcommand;
                outStr = WAIT_STRING;
                break;
            case END_STRING:
                cmd = Command.endcommand;
                outStr = END_STRING;
                break;
            case NEWSEG_STRING:
                outStr = newSegment();
                cmd = Command.newsegment;
                break;
            case SIMM_STRING:
                outStr = calSimm();
                cmd = Command.simmetrico;
                break;
        }
        return outStr;
    }

    private String newSegment(){
        System.out.println("creating a new segment");
        String exampleInput = "1,2,3,4";
        out.println("inserisci segment data");
        out.println("example > " +exampleInput);
        out.print(">");
        try{
            String dataInput = in.readLine();
            String[] pointCoordinates = dataInput.split(",");
            double x, y, x1, y1;
            x = Double.parseDouble(pointCoordinates[0]);
            y = Double.parseDouble(pointCoordinates[1]);
            x1 = Double.parseDouble(pointCoordinates[2]);
            y1 = Double.parseDouble(pointCoordinates[3]);

            Point p = new Point(x, y);
            Point p1 = new Point(x1, y1);

            out.println("Successffly created 2 new points");
            return p.toString() + " " + p1.toString();
        }catch(IOException e){
            e.printStackTrace();
        }
        return "";
    }

    private String calSimm(){
        System.out.println("Calcolo del simmetrico");
        out.println("Inserisci coordinate del punto di cui calcolare il simmetrico");
        String example = "1,2";
        out.println("Example: "+example);
        out.print("> ");

        try{
            String dataInput = in.readLine();
            String[] pointCoordinates = dataInput.split(",");
            double x,y;
            x = Double.parseDouble(pointCoordinates[0]);
            y = Double.parseDouble(pointCoordinates[1]);

            Point p = new Point(x, y);
            Segment sgm = new Segment();
            Point simm = sgm.simmetric(p);
            System.out.println("Simmetrico calcolato");
            return simm.toString();

        }catch(IOException e){
            e.printStackTrace();
        }
        return "";
    }

}
