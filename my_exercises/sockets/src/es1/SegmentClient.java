package es1;

import java.net.Socket;
import java.net.InetAddress;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.net.UnknownHostException;

public class SegmentClient{

    private static final int port = 8080;

    private static String readCoordinates(Scanner sc){
        System.out.print("x: ");
        String x = sc.nextLine();
        System.out.print("\ny: ");
        String y = sc.nextLine();
        return x + "," + y;
    }

    public static void main(String[] args) throws IOException {
        Socket s = null;
        try{
            InetAddress addr = InetAddress.getLocalHost();
            try{
                s = new Socket(addr, port);
                BufferedReader inStream = new BufferedReader(
                        new InputStreamReader(s.getInputStream()));
                PrintWriter outStream = new PrintWriter(
                        new BufferedWriter(new OutputStreamWriter(
                                s.getOutputStream())), true);
                Scanner sc = new Scanner(System.in);
                String command = "";
                System.out.println("awaiting command");
                while(!(command = sc.nextLine()).equals("END")){

                    if(command.equals("NEW_SEGMENT")){
                        String p1Coords = readCoordinates(sc);
                        String p2Coords = readCoordinates(sc);
                        String toSend = p1Coords + " " + p2Coords;
                        outStream.println("NEW_SEGMENT");
                        outStream.println(toSend);
                        String response = inStream.readLine();
                        if(response.equals("OK"))
                            System.out.println("segment successfully added to list");
                        else{
                            System.out.println("es1.Segment not added to list");
                        }
                    }
                    else if(command.equals("SYMMETRICAL")){
                        String coords = readCoordinates(sc);
                        outStream.println("SYMMETRICAL");
                        outStream.println(coords);
                        String response = inStream.readLine();
                        if(!response.equals("KO")){
                            String[] tmpCoords = response.split(",");
                            Point simm = new Point(Double.parseDouble(tmpCoords[0]), Double.parseDouble(tmpCoords[1]));
                            System.out.printf("Simm received back: %d,%d\n"+simm.getX(), simm.getY());
                        }
                        else
                            System.out.println("es1.Point does not exists in list, add it first!");
                    }
                    else if(command.equals("GET_LIST")){
                        String p = "";
                        LinkedList<Point> list = new LinkedList<Point>();
                        System.out.println("Getting list");
                        outStream.println("GET_LIST");
                        while(!(p = inStream.readLine()).equals("DONE")){
                            System.out.println("new string received");
                            String[] tmp = p.split(",");
                            Point x = new Point(Double.parseDouble(tmp[0]),
                                    Double.parseDouble(tmp[1]));
                            list.add(x);
                            System.out.printf("Local point list size: %d\n", list.size());
                        }
                        System.out.println("printing list received");
                        for(Point t : list){
                            System.out.println(t.toString());
                        }
                    }
                    else
                        System.out.println("Command not recognized");
                    System.out.println("awaiting command");
                }
                System.out.println("Terminating connection");
                inStream.close();
                outStream.close();
                sc.close();
                outStream.println("END");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch(UnknownHostException unknownHostException){
            unknownHostException.printStackTrace();
        }finally {
            System.out.println("Closing socket");
            s.close();
        }

    }

}