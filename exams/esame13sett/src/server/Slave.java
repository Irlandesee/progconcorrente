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

import serverinterface.ServerInterface;
import tavola.Tavola;

public class Slave extends Thread implements ServerInterface{

    private static int slaveID = 0;
    private Server serv;
    private Socket s;
    private Tavola t;
    private char segnapostoAssegnato;
    private ObjectInput inputStreamObject;
    private ObjectOutput outputStreamObject;
    private BufferedReader inStream;
    private PrintWriter outStream;
    private Scanner sc;

    private final String NEW_GAME = "NEW_GAME";
    private final String QUIT_GAME = "QUIT_GAME";
    private final String WAIT = "WAIT";
    private final String NEXT_MOVE = "NEXT_MOVE";


    public Slave(Server _serv, Socket _s, Tavola _t, char _segnapostoAssegnato){
        serv = _serv;
        s = _s;
        t = _t;
        segnapostoAssegnato = _segnapostoAssegnato;
        this.setName("Slave_"+slaveID);
        slaveID++;
        init();

    }

    private void init(){
        try{
            System.out.println("creazione degli stream di IO");
            inputStreamObject = new ObjectInputStream(s.getInputStream());
            outputStreamObject = new ObjectOutputStream(s.getOutputStream());
            inStream = new BufferedReader(new InputStreamReader(s.getInputStream()));
            outStream = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())));
            sc = new Scanner(System.in);
        }catch(IOException e){
            System.out.println("Errore nella apertura degli stream IO!");
            e.printStackTrace();
        }
    }

    private char getSegnaposto(){
        return this.segnapostoAssegnato;
    }

    public void setSegnaposto(char _segnapostoAssegnato){
        segnapostoAssegnato = _segnapostoAssegnato;
    }

    public void run(){
        String inboundCommand = "";
        while(!s.isClosed()){
            try{
                System.out.printf("%s: in attesa di comandi\n", this.getName());
                inboundCommand = inStream.readLine();}
            catch(IOException e){
                System.out.printf("%s: errore durante lettura istream\n", this.getName());e.printStackTrace();
            }
            if(inboundCommand.equals(NEW_GAME)){
                serv.initGame(); //la prima mossa spetta al giocatore che non ha creato la partita.
                Tavola temp = serv.getTavola();
                try{
                    outputStreamObject.writeObject(temp);
                }catch(IOException e1){
                    System.out.printf("%s: errore nella scrittura della tavola su outputStreamObject", this.getName());
                    e1.printStackTrace();
                }
                System.out.printf("%s: Nuovo gioco creato, il primo turno spetta all'altro giocatore.\n", this.getName());
                outStream.println(NEXT_MOVE); //dico al "mio" client di dare la prossima mossa
            }
            else if(inboundCommand.equals(NEXT_MOVE)){//leggo dallo stream la mossa del "mio client"
                try{
                    String mossa = inStream.readLine();
                    String[] coordinate = mossa.split(" ");
                    int x = Integer.parseInt(coordinate[0]);
                    int y = Integer.parseInt(coordinate[1]);
                    System.out.printf("%s: coordinate lette: x:%d, y:%d\n", this.getName(), x, y);
                    if(t.set(x, y, this.getSegnaposto())){
                        System.out.printf("%s: coordinate valide, invio nuova tavola...\n", this.getName());
                        char[][] newCelle = t.getCelle();
                        t.setCelle(newCelle);
                        serv.setTavola(t);
                        outputStreamObject.writeObject(t);
                    }
                    else{
                        System.out.printf("%s: coordinate non valide, invio vecchia tavola...\n", this.getName());
                        outputStreamObject.writeObject(serv.getTavola());
                    }
                    outStream.println(WAIT); //Ora è il mio turno, dico all altro giocatore di aspettare
                }catch(IOException e2){
                    System.out.printf("%s: errore nella lettura prossima mossa del client\n", this.getName());
                    e2.printStackTrace();
                }
            }
            else if(inboundCommand.equals(WAIT)){//aspetto il mio turno
                System.out.printf("%s: vado in wait", this.getName());
                synchronized (this){ //vado in wait, aspetto di venire svegliato
                    try{
                        wait();
                    }catch(InterruptedException ie){ie.printStackTrace();}}
            }
            else if(inboundCommand.equals(QUIT_GAME)){//client si è disconnesso
                System.out.printf("%s: client si è disconnesso\n", this.getName());
            }

        }
    }

    private void closeStreams(){
        System.out.println("Chiusura stream IO!");
        try{
            inputStreamObject.close();
            outputStreamObject.close();
            inStream.close();
            outStream.close();
        }catch(IOException e1){
            System.out.println("Errore nella chiusura degli stream IO");
            e1.printStackTrace();
        }
    }

    public void reset(){
        t.reset();
    }

    public boolean set(int r, int c, char s){
        return t.set(r, c, s);
    }

    public void show(){
        t.show();
    }

    public Winner cellOwner(char x){
        return t.cellOwner(x);
    }

    public Winner whoWins(){
        return t.whoWins();
    }

}
