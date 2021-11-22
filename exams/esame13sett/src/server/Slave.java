package server;

import java.net.Socket;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectInput;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import tavolainterface.TavolaInterface;
import tavola.Tavola;

public class Slave extends Thread{

    private static int slaveID = 0;
    private Server serv;
    private Socket s;
    private Tavola t;
    private ObjectInput inputStreamObject;
    private ObjectOutput outputStreamObject;
    private BufferedReader inStream;
    private PrintWriter outStream;


    public Slave(Server _serv, Socket _s, Tavola _t){
        serv = _serv;
        s = _s;
        t = _t;
        slaveID++;
        start();
    }

    public void run(){
        boolean done = false;
        String comandoClient = "";
        try{

            inStream = new BufferedReader(new InputStreamReader(s.getInputStream()));
            outStream = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
            outputStreamObject = new ObjectOutputStream(s.getOutputStream());
            while(!done){
                System.out.println("Attendo comandi dal client");
                try{
                    while((comandoClient = inStream.readLine())!= null)
                        System.out.println(comandoClient);
                }catch(IOException e){
                    System.err.println("Errore mentre leggevo comando client");
                    e.printStackTrace();
                }
                System.out.println("Comando ricevuto: "+comandoClient);

                switch(comandoClient){
                    case "NEWGAME":
                        if(serv.getSlaveListSize() == 0){
                            serv.initGame();
                            System.out.println("Nuovo gioco inizializzato");
                            outStream.println("OK");
                        }
                        System.out.println("Nessun nuovo gioco inizializzato");
                        outStream.println("NOT_OK");
                        break;
                    case "RESET":
                        if(serv.getSlaveListSize() == 1){
                            //Reset
                            serv.initGame();
                            System.out.println("Gioco resettato");
                        }
                        break;
                    case "CONNECT":
                        if(serv.getSlaveListSize() > 0){
                            System.out.println("Nuovo Cliente inizia a giocare");
                            outStream.println("OK");
                        }
                        else
                            outStream.println("NOT_OK");
                        break;
                    case "GET_TAVOLA":
                        if((serv.getTavola()) != null){
                            System.out.println("Richiesta tavola in corso");
                            outStream.println("OK");
                            try{
                                outputStreamObject.writeObject(serv.getTavola());
                            }catch(IOException e){
                                System.err.println("Errore mentre scrivevo tavola sullo stream");
                                e.printStackTrace();
                            }
                        }
                        outStream.println("NOT_OK");
                        break;
                    case "NEXT_MOVE":
                        System.out.println("Tentativo lettura coordinate in corso");
                        try{
                            int r = inStream.read();
                            int c = inStream.read();
                            char s = (char) inStream.read();
                        }catch(IOException e){
                            System.err.println("Errore lettura coordinate");
                            e.printStackTrace();
                        }
                        //check coordinate
                        outStream.println("MOVE_OK");
                        break;
                    case "WHO_WINS":
                        System.out.println("Checking winner");
                        String winner = serv.checkWinner();
                        outStream.println(winner);
                        break;
                    case "QUIT":
                        System.out.println("Client si è disconnesso");
                        done = true;
                        break;
                    default:
                        System.out.println("Comando non riconosciuto...");
                        break;
                }
        }
        }catch(IOException e){
            System.err.println("Errore mentre creavo stream IO");
            e.printStackTrace();
        }

        System.out.println("Chiudo il socket e stream io");
        try{
            s.close();
            inStream.close();
            outStream.close();
            outputStreamObject.close();
            inputStreamObject.close();
        }catch(IOException e){
            System.err.println("Errore nella chiusura stream IO e socket");
            e.printStackTrace();
        }
    }

}
